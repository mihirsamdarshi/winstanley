package winstanley.structure

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import winstanley.psi._

object WdlDocumentUtils {
  implicit final class EnhancedPsiElement(val psiElement: PsiElement) extends AnyVal {
    def childTaskBlocks: Set[WdlTaskBlock] = (psiElement.getChildren collect {
      case t: WdlTaskBlock => t
    }).toSet

    def childWorkflowBlocks: Set[WdlWorkflowBlock] = (psiElement.getChildren collect {
      case w: WdlWorkflowBlock => w
    }).toSet

    def contentRange: Option[TextRange] = psiElement match {
      case _: WdlTaskBlock | _: WdlWorkflowBlock | _: WdlTaskOutputs | _: WdlWfOutputs | _: WdlCallBlock | _: WdlScatterBlock | _: WdlIfStmt =>
        interBraceContentRange(psiElement, WdlTypes.LBRACE, WdlTypes.RBRACE)
      case wcb: WdlCommandBlock => interBraceContentRange(wcb, WdlTypes.COMMAND_DELIMITER_OPEN, WdlTypes.COMMAND_DELIMITER_CLOSE)
      case _: WdlRuntimeBlock | _: WdlParameterMetaBlock =>
        mapContainingContentRange(psiElement)
      case _ => None
    }

    private def mapContainingContentRange(psiElement: PsiElement): Option[TextRange] = psiElement.getChildren.collectFirst {
      case m: WdlMap => interBraceContentRange(m, WdlTypes.LBRACE, WdlTypes.RBRACE)
    }.flatten

    private def interBraceContentRange(psiElement: PsiElement, ltype: IElementType, rtype: IElementType): Option[TextRange] = {
      for {
        lbrace <- Option(psiElement.getNode.findChildByType(ltype))
        rbrace <- Option(psiElement.getNode.findChildByType(rtype))
      } yield new TextRange(lbrace.getTextRange.getStartOffset + 1, rbrace.getTextRange.getEndOffset - 1)
    }
  }

  implicit final class EnhancedWdlTaskBlock(val wdlTaskBlock: WdlTaskBlock) extends AnyVal {
    def taskName: String = wdlTaskBlock.getNode.findChildByType(winstanley.psi.WdlTypes.TASK_IDENTIFIER_DECL).getText
  }

  implicit final class EnhancedWdlWorkflowBlock(val wdlWorkflowBlock: WdlWorkflowBlock) extends AnyVal {
    def workflowName: String = wdlWorkflowBlock.getNode.findChildByType(winstanley.psi.WdlTypes.WORKFLOW_IDENTIFIER_DECL).getText
  }
}
