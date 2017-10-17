package winstanley

import com.intellij.lang.annotation.{AnnotationHolder, Annotator}
import com.intellij.psi.PsiElement
import winstanley.psi.WdlVariableLookup
import winstanley.structure.WdlImplicits._


class WdlAnnotator extends Annotator {
  override def annotate(psiElement: PsiElement, annotationHolder: AnnotationHolder): Unit = psiElement match {
    case value: WdlVariableLookup =>

      // If this value is an identifier, make sure that it's been declared somewhere (either in a declaration or in a scatter)
      value.getIdentifierNode foreach { identifier =>
        val identifierText = identifier.getText
        val declarationNames = value.findDeclarationsAvailableInScope.flatMap(_.declaredValueName)

        if (!declarationNames.contains(identifierText)) {
          annotationHolder.createErrorAnnotation(identifier.getTextRange, s"No declaration found for '${identifier.getText}'")
        }
      }
    case _ => ()
  }
}
