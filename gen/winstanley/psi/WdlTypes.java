// This is a generated file. Not intended for manual editing.
package winstanley.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import winstanley.psi.impl.*;

public interface WdlTypes {

  IElementType ALIAS = new WdlElementType("ALIAS");
  IElementType PROPERTY = new WdlElementType("PROPERTY");
  IElementType WF_BODY_ELEMENT = new WdlElementType("WF_BODY_ELEMENT");
  IElementType WORKFLOW_BLOCK = new WdlElementType("WORKFLOW_BLOCK");

  IElementType AS = new WdlTokenType("AS");
  IElementType CALL = new WdlTokenType("CALL");
  IElementType COMMENT = new WdlTokenType("COMMENT");
  IElementType CRLF = new WdlTokenType("CRLF");
  IElementType FQN = new WdlTokenType("FQN");
  IElementType IDENTIFIER = new WdlTokenType("IDENTIFIER");
  IElementType KEY = new WdlTokenType("KEY");
  IElementType LBRACE = new WdlTokenType("LBRACE");
  IElementType RBRACE = new WdlTokenType("RBRACE");
  IElementType SEPARATOR = new WdlTokenType("SEPARATOR");
  IElementType VALUE = new WdlTokenType("VALUE");
  IElementType WORKFLOW = new WdlTokenType("WORKFLOW");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ALIAS) {
        return new WdlAliasImpl(node);
      }
      else if (type == PROPERTY) {
        return new WdlPropertyImpl(node);
      }
      else if (type == WF_BODY_ELEMENT) {
        return new WdlWfBodyElementImpl(node);
      }
      else if (type == WORKFLOW_BLOCK) {
        return new WdlWorkflowBlockImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
