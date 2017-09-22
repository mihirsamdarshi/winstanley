package winstanley

import com.intellij.lang.annotation.{AnnotationHolder, Annotator}
import com.intellij.psi.PsiElement
import winstanley.psi.WdlValue
import winstanley.structure.WdlImplicits._


class WdlAnnotator extends Annotator {
  override def annotate(psiElement: PsiElement, annotationHolder: AnnotationHolder): Unit = psiElement match {
    case value: WdlValue =>

      // If this value is an identifier, make sure that it's been declared somewhere (either in a declaration or in a scatter)
      value.asIdentifierNode foreach { identifier =>
        val declarationNames = value.findDeclarationsAvailableInScope.map(_.declaredValueName) collect { case Some(d) => d }

        val scatterVariableName = for {
          outerscatter <- value.findContainingScatter
          scatterVariable <- outerscatter.getIdentifierNode
        } yield scatterVariable.getText

        val availableValueNames = declarationNames ++ scatterVariableName

        val identifierText = identifier.getText
        if (!availableValueNames.contains(identifierText)) {
          annotationHolder.createErrorAnnotation(identifier.getTextRange, s"No declaration found for '${identifier.getText}'")
        }
      }
    case _ => ()
  }
}
