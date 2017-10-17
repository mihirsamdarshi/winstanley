package winstanley.structure

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import winstanley.psi._

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

      psiElement match {
        case _: WdlTaskBlock | _: WdlWorkflowBlock | _: WdlTaskOutputs | _: WdlWfOutputs | _: WdlCallBlock | _: WdlScatterBlock | _: WdlIfStmt =>
          interBraceContentRange(psiElement, WdlTypes.LBRACE, WdlTypes.RBRACE)
        case wcb: WdlCommandBlock =>
          interBraceContentRange(wcb, WdlTypes.COMMAND_DELIMITER_OPEN, WdlTypes.COMMAND_DELIMITER_CLOSE)
        case _: WdlRuntimeBlock | _: WdlParameterMetaBlock =>
          mapContainingContentRange(psiElement)
        case _ => None
      }
    }

    def findContainingScatter: Option[WdlScatterBlock] = {
      Option(psiElement.getParent) flatMap {
        case s: WdlScatterBlock => Option(s)
        case other => other.findContainingScatter
      }
    }

    def findDeclarationsInInnerScopes: Set[WdlDeclaration] = {
      def expandChild(child: PsiElement): Set[WdlDeclaration] = child match {
        case d: WdlDeclaration => Set(d)
        case b: WdlWfBodyElement if b.getDeclaration != null => Set(b.getDeclaration)
        case other =>
          other.findDeclarationsInInnerScopes
      }

      psiElement.getChildren.toSet flatMap expandChild
    }

    def findDeclarationsAvailableInScope: Set[WdlNamedElement] = {
      Option(psiElement.getParent) map { parent =>
        val siblings = parent.getChildren.filterNot(_ eq psiElement)
        val siblingDeclarations = siblings collect {
          case d: WdlDeclaration => Set(d)
          case b: WdlWfBodyElement if b.getDeclaration != null => Set(b.getDeclaration)
          case b: WdlWfBodyElement if b.getScatterBlock != null => b.getScatterBlock.findDeclarationsInInnerScopes
          case b: WdlWfBodyElement if b.getIfStmt != null => b.getIfStmt.findDeclarationsInInnerScopes
        }

        val scatterDeclaration = Option(parent) collect {
          case b: WdlWfBodyElement if b.getScatterBlock != null => b.getScatterBlock.getScatterDeclaration
        }

        parent.findDeclarationsAvailableInScope ++ siblingDeclarations.flatten ++ scatterDeclaration
      } getOrElse Set.empty
    }

    def getIdentifierNode: Option[ASTNode] = Option(psiElement.getNode.findChildByType(WdlTypes.IDENTIFIER))
  }

}
