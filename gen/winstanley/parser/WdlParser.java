// This is a generated file. Not intended for manual editing.
package winstanley.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static winstanley.psi.WdlTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class WdlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ALIAS) {
      r = alias(b, 0);
    }
    else if (t == PROPERTY) {
      r = property(b, 0);
    }
    else if (t == WF_BODY_ELEMENT) {
      r = wf_body_element(b, 0);
    }
    else if (t == WORKFLOW_BLOCK) {
      r = workflow_block(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return wdlFile(b, l + 1);
  }

  /* ********************************************************** */
  // AS IDENTIFIER
  public static boolean alias(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "alias")) return false;
    if (!nextTokenIs(b, AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AS, IDENTIFIER);
    exit_section_(b, m, ALIAS, r);
    return r;
  }

  /* ********************************************************** */
  // property|COMMENT|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (KEY? SEPARATOR VALUE?) | KEY
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, "<property>", KEY, SEPARATOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<property>");
    r = property_0(b, l + 1);
    if (!r) r = consumeToken(b, KEY);
    exit_section_(b, l, m, PROPERTY, r, false, null);
    return r;
  }

  // KEY? SEPARATOR VALUE?
  private static boolean property_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property_0_0(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    r = r && property_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEY?
  private static boolean property_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_0")) return false;
    consumeToken(b, KEY);
    return true;
  }

  // VALUE?
  private static boolean property_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_2")) return false;
    consumeToken(b, VALUE);
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean wdlFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wdlFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "wdlFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // CALL FQN alias?
  public static boolean wf_body_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_body_element")) return false;
    if (!nextTokenIs(b, CALL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CALL, FQN);
    r = r && wf_body_element_2(b, l + 1);
    exit_section_(b, m, WF_BODY_ELEMENT, r);
    return r;
  }

  // alias?
  private static boolean wf_body_element_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_body_element_2")) return false;
    alias(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // WORKFLOW IDENTIFIER LBRACE wf_body_element* RBRACE
  public static boolean workflow_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "workflow_block")) return false;
    if (!nextTokenIs(b, WORKFLOW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, WORKFLOW, IDENTIFIER, LBRACE);
    r = r && workflow_block_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, WORKFLOW_BLOCK, r);
    return r;
  }

  // wf_body_element*
  private static boolean workflow_block_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "workflow_block_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!wf_body_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "workflow_block_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

}
