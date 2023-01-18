package org.broadinstitute.winstanley

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.{FoldingBuilderEx, FoldingDescriptor}
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import org.broadinstitute.winstanley.structure.WdlImplicits._

class WdlFoldingBuilder extends FoldingBuilderEx {

  private def foldingRegions(psiElement: PsiElement): Array[FoldingDescriptor] = {
    (psiElement.getChildren flatMap foldingRegions) ++ (psiElement.contentRange map { new FoldingDescriptor(psiElement.getNode, _)})
  }

  override def buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array[FoldingDescriptor] = foldingRegions(root)

  override def getPlaceholderText(astNode: ASTNode): String = " ... "

  override def isCollapsedByDefault(astNode: ASTNode): Boolean = false
}
