package org.broadinstitute.winstanley.structure

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.{PsiElement, PsiFile}
import com.intellij.psi.tree.{IElementType, TokenSet}
import org.broadinstitute.winstanley.psi._
import org.broadinstitute.winstanley.psi.impl.WdlTaskBlockImpl

object WdlImplicits {
  implicit final class EnhancedPsiElement(val psiElement: PsiElement) extends AnyVal {

    def contentRange: Option[TextRange] = {
      def mapContainingContentRange(psiElement: PsiElement): Option[TextRange] = psiElement.getChildren.collectFirst {
        case m: WdlMap => interBraceContentRange(m, WdlTypes.LBRACE, WdlTypes.RBRACE)
      }.flatten

      def interBraceContentRange(psiElement: PsiElement, ltype: IElementType, rtype: IElementType): Option[TextRange] = {
        for {
          lbrace <- Option(psiElement.getNode.findChildByType(ltype))
          rbrace <- Option(psiElement.getNode.findChildByType(rtype))
        } yield new TextRange(lbrace.getTextRange.getStartOffset + 1, rbrace.getTextRange.getEndOffset - 1)
      }

      val potentialRange = psiElement match {
        case _: WdlTaskBlock | _: WdlWorkflowBlock | _: WdlTaskOutputs | _: WdlWfOutputs | _: WdlCallBlock | _: WdlScatterBlock | _: WdlIfStmt =>
          interBraceContentRange(psiElement, WdlTypes.LBRACE, WdlTypes.RBRACE)
        case wcb: WdlCommandBlock =>
          interBraceContentRange(wcb, WdlTypes.COMMAND_DELIMITER_OPEN, WdlTypes.COMMAND_DELIMITER_CLOSE)
        case _: WdlRuntimeBlock | _: WdlParameterMetaBlock =>
          mapContainingContentRange(psiElement)
        case _ => None
      }

      potentialRange collect { case range if range.getLength > 0 => range }
    }

    def findContainingScatter: Option[WdlScatterBlock] = {
      Option(psiElement.getParent) flatMap {
        case s: WdlScatterBlock => Option(s)
        case other => other.findContainingScatter
      }
    }

    def findReferencesInInnerScopes: Set[WdlNamedElement] = {
      def expandChild(child: PsiElement): Set[WdlNamedElement] = child match {
        case d: WdlDeclaration => Set(d)
        case b: WdlWfBodyElement if b.getDeclaration != null => Set(b.getDeclaration)
        case b: WdlWfBodyElement if b.getCallBlock != null => findAliasOrCallDeclaration(b.getCallBlock)
        case other => other.findReferencesInInnerScopes
      }
      psiElement.getChildren.toSet flatMap expandChild
    }

    private def findAliasOrCallDeclaration(callBlock: WdlCallBlock): Set[WdlNamedElement] = {
      Option(callBlock.getCallAlias) match {
        case Some(alias) => Set(alias)
        case None => Set(callBlock.getCallableLookup)
      }
    }

    def findReferencesInScope: Set[WdlNamedElement] = {
      Option(psiElement.getParent) map { parent =>
        val siblings = parent.getChildren.filterNot(_ eq psiElement)

        val siblingDeclarations = (siblings collect {
          case ne: WdlNamedElement => Set(ne)
          case wo: WdlWfOutput if wo.getWfOutputDeclaration != null => Set(wo.getWfOutputDeclaration)
          case b: WdlWfBodyElement if b.getDeclaration != null => Set(b.getDeclaration)
          case b: WdlWfBodyElement if b.getScatterBlock != null => b.getScatterBlock.findReferencesInInnerScopes
          case b: WdlWfBodyElement if b.getIfStmt != null => b.getIfStmt.findReferencesInInnerScopes
          case b: WdlWfBodyElement if b.getInputBlock != null => b.getInputBlock.findReferencesInInnerScopes
          case b: WdlWfBodyElement if b.getCallBlock != null => findAliasOrCallDeclaration(b.getCallBlock)

          case t: WdlTaskSection if t.getInputBlock != null => t.getInputBlock.findReferencesInInnerScopes
          case t: WdlTaskSection if t.getDeclaration != null => Set(t.getDeclaration)
        }).map(_.toSet[WdlNamedElement])

        val scatterDeclaration = Option(parent) collect {
          case b: WdlWfBodyElement if b.getScatterBlock != null => b.getScatterBlock.getScatterDeclaration
        }

        parent.findReferencesInScope ++ siblingDeclarations.flatten ++ scatterDeclaration
      } getOrElse Set.empty
    }

    def getIdentifierNode: Option[ASTNode] = Option(psiElement.getNode.findChildByType(WdlTypes.IDENTIFIER))

    def getTaskIdentifierNode: Option[ASTNode] = Option(psiElement.getNode.findChildByType(WdlTypes.TASK_IDENTIFIER_DECL))

    def getWdlFileElement: PsiElement = {
      psiElement match {
        case p: WdlDraft2File => p
        case p: WdlVersion10File => p
        case _ =>
          psiElement.getParent.getWdlFileElement
      }
    }

    /**
      * Recurses until it has reached the PsiFile node and finds all task blocks, then returns the task declaration for
      * each found task block.
      */
    def findTaskDeclarationsInScope: Set[WdlNamedTaskElement] = {
      psiElement.findTasksInScope.map(_.getTaskDeclaration).toSet
    }

    def findTasksInScope: Set[WdlTaskBlockImpl] = {
      val taskContainer = psiElement match {
        case p: WdlDraft2File => Some(p)
        case p: WdlVersion10File => Some(p)
        case _ => None
      }

      taskContainer match {
        case Some(p) =>
          val taskBlocks = p.getChildren.collect {
            case taskBlock: WdlTaskBlockImpl => taskBlock
          }
          taskBlocks.toSet
        case None => psiElement.getParent.findTasksInScope
      }
    }
  }

  implicit final class EnhancedWdlCallableLookup(val wdlCallableLookup: WdlCallableLookup) extends AnyVal {
    def getCalledIdentifier: String = {
      wdlCallableLookup.getFullyQualifiedName.getNode.getChildren(TokenSet.create(WdlTypes.IDENTIFIER)).toList.map(_.getText).mkString(".")
    }
  }

}
