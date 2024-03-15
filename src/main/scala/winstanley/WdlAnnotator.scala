package winstanley

import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.{
  AnnotationHolder,
  Annotator,
  HighlightSeverity
}
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.tree.TokenSet
import winstanley.psi._
import winstanley.psi.impl.WdlTaskBlockImpl
import winstanley.structure.WdlImplicits._

import scala.collection.mutable
import scala.jdk.CollectionConverters._

class WdlAnnotator extends Annotator {

  /** Makes sure that the passed PSI element is one of the cases, then determines if it's been declared somewhere
    */
  override def annotate(
      psiElement: PsiElement,
      annotationHolder: AnnotationHolder
  ): Unit = psiElement match {
    case dep: LeafPsiElement
        if dep.getElementType == WdlTypes.DEPRECATED_COMMAND_VAR_OPENER =>
      val commandVar = dep.getParent.getParent
      annotationHolder
        .newAnnotation(
          HighlightSeverity.WEAK_WARNING,
          "Deprecated placeholder style: Use ~{ ... } for WDL 1.0 (draft-3) onwards to match 'command <<<' section placeholders"
        )
        .range(commandVar)
        .create()

    case task: WdlTaskBlock =>
      if (
        !task.getTaskSectionList.asScala
          .exists(section => Option(section.getCommandBlock).isDefined)
      ) {
        annotationHolder
          .newAnnotation(
            HighlightSeverity.ERROR,
            "No command specified for task"
          )
          .range(task.getTaskDeclaration.getNameIdentifier.getTextRange)
          .create()
      }
      if (
        !task.getTaskSectionList.asScala
          .exists(section => Option(section.getRuntimeBlock).isDefined)
      ) {
        annotationHolder
          .newAnnotation(
            HighlightSeverity.WEAK_WARNING,
            "Non-portable task section: add a runtime section specifying a docker image"
          )
          .range(task.getTaskDeclaration.getNameIdentifier.getTextRange)
          .create()
      }
      if (
        !task.getTaskSectionList.asScala
          .exists(section => Option(section.getTaskOutputs).isDefined)
      ) {
        annotationHolder
          .newAnnotation(
            HighlightSeverity.WEAK_WARNING,
            "Suspicious lack of task outputs (is this task really idempotent and portable?)"
          )
          .range(task.getTaskDeclaration.getNameIdentifier.getTextRange)
          .create()
      }
    case runtime: WdlRuntimeBlock
        if !runtime.getKvList.asScala.flatMap(kvName).contains("docker") =>
      runtimeKeyword(runtime) foreach { r =>
        annotationHolder
          .newAnnotation(
            HighlightSeverity.WEAK_WARNING,
            "Non-portable runtime section: specify a docker image"
          )
          .range(r.getTextRange)
          .create()
      }
    case taskOutputSection: WdlTaskOutputs
        if taskOutputSection.getOutputKvList.asScala.isEmpty =>
      outputKeyword(taskOutputSection) foreach { r =>
        annotationHolder
          .newAnnotation(
            HighlightSeverity.WEAK_WARNING,
            "Suspicious lack of task outputs (is this task really idempotent and portable?)"
          )
          .range(r.getTextRange)
          .create()
      }

    case value: WdlValueLookup =>
      value.getIdentifierNode foreach { identifier =>
        val identifierText = identifier.getText
        val valueNames =
          value.findReferencesInScope.map(_.getNameIdentifier.getText)

        if (!valueNames.contains(identifierText)) {
          annotationHolder
            .newAnnotation(
              HighlightSeverity.ERROR,
              s"No declaration found for '${identifier.getText}'"
            )
            .range(identifier.getTextRange)
            .create()
        }
      }
    case value: WdlCallableLookup =>
      // TODO (Issue 63): Work out a better way to verify that imports exist and (if local?) that an appropriate task/workflow inside them exist.
      // For now to avoid error highlights, I'll just suppress errors for things that look like FQNs
      if (
        value.getFullyQualifiedName.getNode
          .getChildren(TokenSet.ANY)
          .length == 1
      ) {
        value.getFullyQualifiedName.getIdentifierNode foreach { identifier =>
          val identifierText = identifier.getText
          val taskNames =
            value.findTaskDeclarationsInScope.flatMap(_.declaredValueName)

          if (!taskNames.contains(identifierText)) {
            annotationHolder
              .newAnnotation(
                HighlightSeverity.ERROR,
                s"No task declaration found for '${identifier.getText}'"
              )
              .range(identifier.getTextRange)
              .create()
          }
        }
      } else
        ()
    case declaration: WdlDeclaration =>
      if (psiElement.getWdlFileElement.isInstanceOf[WdlVersion10File])
        if (
          !declaration.getParent
            .isInstanceOf[WdlInputBlock] && declaration.getSetter == null
        )
          annotationHolder
            .newAnnotation(
              HighlightSeverity.ERROR,
              "Immediate assignment required for non-input declaration: do you need to add an 'input { }' section? [WDL 1.0]"
            )
            .range(psiElement)
            .create()

    case wildcardOutput: WdlWfOutputWildcardStatement =>
      if (psiElement.getWdlFileElement.isInstanceOf[WdlDraft2File]) {
        annotationHolder
          .newAnnotation(
            HighlightSeverity.WEAK_WARNING,
            "Declaration style outputs will be required in a later version of WDL"
          )
          .range(wildcardOutput)
          .create()
      } else {
        annotationHolder
          .newAnnotation(
            HighlightSeverity.ERROR,
            "Declaration style outputs are required in WDL 1.0 (draft 3) and later"
          )
          .range(wildcardOutput)
          .create()
      }

    case callBlock: WdlCallBlock =>
      def taskForCall(callBlock: WdlCallBlock): Option[WdlTaskBlockImpl] = {
        val taskName = callBlock.getCallableLookup.getName
        val tasksInScope: Set[WdlTaskBlockImpl] = callBlock.findTasksInScope

        tasksInScope.find(_.getTaskDeclaration.getName == taskName)
      }

      if (psiElement.getWdlFileElement.isInstanceOf[WdlVersion10File]) {
        taskForCall(callBlock) match {
          case Some(task) =>
            // The "type" of a WdlTaskSection is determined by which one of its getXXX() methods returns not-null. Checkmate, Scala nerds.
            val maybeTaskInputSection: Option[WdlTaskSection] =
              task.getTaskSectionList.asScala.find(_.getInputBlock != null)

            val maybeCallInput: Option[WdlCallInput] = Option(
              callBlock.getCallInput
            )

            (maybeCallInput, maybeTaskInputSection) match {
              case (Some(callInput), Some(taskInputSection)) =>
                compareInputListsForCall(
                  psiElement,
                  annotationHolder,
                  callInput,
                  taskInputSection
                )
              case (Some(_), None) =>
                annotationHolder
                  .newAnnotation(
                    HighlightSeverity.ERROR,
                    "Task does not take inputs."
                  )
                  .range(psiElement)
                  .create()
              case (None, Some(taskInputSection)) =>
                // Missing args = all args
                val missingArgs =
                  taskInputSection.getInputBlock.getDeclarationList.asScala
                    .map(_.getName)
                annotationHolder
                  .newAnnotation(
                    HighlightSeverity.WEAK_WARNING,
                    "Unsupplied input(s) " + missingArgs
                      .map("\'" + _ + "\'")
                      .mkString(
                        ", "
                      ) + " must be assigned here or, if this is the root workflow, provided in the inputs JSON."
                  )
                  .range(psiElement)
                  .create()
              case (None, None) => ()
            }

          case None => ()
        }
      }
    case _ => ()
  }

  private def kvName(kv: WdlKv): Option[String] =
    Option(kv.getNode.findChildByType(WdlTypes.IDENTIFIER)).map(_.getText)

  private def runtimeKeyword(r: WdlRuntimeBlock): Option[ASTNode] = Option(
    r.getNode.findChildByType(WdlTypes.RUNTIME)
  )

  private def outputKeyword(r: WdlTaskOutputs): Option[ASTNode] = Option(
    r.getNode.findChildByType(WdlTypes.OUTPUT)
  )

  private def compareInputListsForCall(
      psiElement: PsiElement,
      annotationHolder: AnnotationHolder,
      callInput: WdlCallInput,
      taskInputSection: WdlTaskSection
  ): Unit = {
    // In the interest of unambiguous naming, this method (ab)uses the formal definitions of parameter (declared on method) and argument (passed to method)
    val callArgumentMappings: List[WdlMapping] =
      callInput.getMappingList.asScala.toList
    val callArguments =
      callArgumentMappings.map(_.getNode.getText.split(Array(' ', '=')).head)

    // Check duplicate arguments
    val distinctArguments = callArguments.distinct
    val duplicateArgumentInstances =
      callArguments diff distinctArguments // For args ['a', 'b', 'c', 'c', 'c'] this is ['c', 'c']
    val duplicateArguments =
      duplicateArgumentInstances.distinct // ['c', 'c'] -> ['c']

    duplicateArguments foreach { argument =>
      annotationHolder
        .newAnnotation(
          HighlightSeverity.ERROR,
          "Repeated input \'" + argument + "\'"
        )
        .range(psiElement)
        .create()
    }

    val parameters: mutable.Buffer[WdlDeclaration] =
      taskInputSection.getInputBlock.getDeclarationList.asScala

    val missingArgs: mutable.Buffer[String] = parameters flatMap { parameter =>
      if (
        parameter.getTypeE.getText.endsWith("?") || parameter.getSetter != null
      ) {
        None
      } else {
        if (!callArguments.contains(parameter.getName))
          Some(parameter.getName)
        else
          None
      }
    }

    // Multiple annotations on the same element are supported and work well visually
    if (missingArgs.nonEmpty)
      annotationHolder
        .newAnnotation(
          HighlightSeverity.WEAK_WARNING,
          "Unsupplied input(s) " + missingArgs
            .map("\'" + _ + "\'")
            .mkString(
              ", "
            ) + " must be assigned here or, if this is the root workflow, provided in the inputs JSON."
        )
        .range(psiElement)
        .create()

    val extraArgs: Seq[String] = callArguments flatMap { argument =>
      if (!parameters.map(_.getName).contains(argument))
        Some(argument)
      else
        None
    }

    if (extraArgs.nonEmpty)
      annotationHolder
        .newAnnotation(
          HighlightSeverity.ERROR,
          "Unexpected inputs(s) for task: " + extraArgs.mkString(", ")
        )
        .range(psiElement)
        .create()
  }

}
