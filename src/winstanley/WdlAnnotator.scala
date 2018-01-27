package winstanley

import com.intellij.lang.annotation.{AnnotationHolder, Annotator}
import com.intellij.psi.PsiElement
import winstanley.psi.{WdlCallableLookup, WdlVariableLookup}
import winstanley.structure.WdlImplicits._


class WdlAnnotator extends Annotator {

  /**
    * Makes sure that the passed PSI element is one of the cases, then determines if it's been declared somewhere
    */
  override def annotate(psiElement: PsiElement, annotationHolder: AnnotationHolder): Unit = psiElement match {
    case value: WdlVariableLookup =>

      value.getIdentifierNode foreach { identifier =>
        val identifierText = identifier.getText
        val declarationNames = value.findDeclarationsAvailableInScope.flatMap(_.declaredValueName)

        if (!declarationNames.contains(identifierText)) {
          annotationHolder.createErrorAnnotation(identifier.getTextRange, s"No declaration found for '${identifier.getText}'")
        }
      }
    case value: WdlCallableLookup =>

      value.getFullyQualifiedName.getIdentifierNode foreach { identifier =>
        val identifierText = identifier.getText
        val declarationNames = value.findTaskDeclarationsAvailableInScope.flatMap(_.declaredValueName)

        if (!declarationNames.contains(identifierText)) {
          annotationHolder.createErrorAnnotation(identifier.getTextRange, s"No task declaration found for '${identifier.getText}'")
        }
      }
    case _ => ()
  }
}
