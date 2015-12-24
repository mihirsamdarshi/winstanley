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
    else if (t == ARRAY_LITERAL) {
      r = array_literal(b, 0);
    }
    else if (t == ARRAY_OR_MAP_LOOKUP) {
      r = array_or_map_lookup(b, 0);
    }
    else if (t == CALL_BLOCK) {
      r = call_block(b, 0);
    }
    else if (t == CALL_BODY) {
      r = call_body(b, 0);
    }
    else if (t == CALL_INPUT) {
      r = call_input(b, 0);
    }
    else if (t == COMMAND_BLOCK) {
      r = command_block(b, 0);
    }
    else if (t == COMMAND_VAR) {
      r = command_var(b, 0);
    }
    else if (t == COMMAND_VAR_OPTION) {
      r = command_var_option(b, 0);
    }
    else if (t == DECLARATION) {
      r = declaration(b, 0);
    }
    else if (t == EXPRESSION) {
      r = expression(b, 0);
    }
    else if (t == EXPRESSION_DASH) {
      r = expression_dash(b, 0);
    }
    else if (t == FLOAT_VALUE) {
      r = float_value(b, 0);
    }
    else if (t == FQN) {
      r = fqn(b, 0);
    }
    else if (t == FUNCTION_CALL) {
      r = function_call(b, 0);
    }
    else if (t == IF_STMT) {
      r = if_stmt(b, 0);
    }
    else if (t == IMPORT_NAMESPACE) {
      r = import_namespace(b, 0);
    }
    else if (t == IMPORT_STMT) {
      r = import_stmt(b, 0);
    }
    else if (t == INFIX_OPERATOR) {
      r = infix_operator(b, 0);
    }
    else if (t == INTEGER_VALUE) {
      r = integer_value(b, 0);
    }
    else if (t == KV) {
      r = kv(b, 0);
    }
    else if (t == MAP) {
      r = map(b, 0);
    }
    else if (t == MAP_KV) {
      r = map_kv(b, 0);
    }
    else if (t == MAP_LITERAL) {
      r = map_literal(b, 0);
    }
    else if (t == MAPPING) {
      r = mapping(b, 0);
    }
    else if (t == MEMBER_ACCESS) {
      r = member_access(b, 0);
    }
    else if (t == META_BLOCK) {
      r = meta_block(b, 0);
    }
    else if (t == OBJECT_LITERAL) {
      r = object_literal(b, 0);
    }
    else if (t == OUTPUT_KV) {
      r = output_kv(b, 0);
    }
    else if (t == PARAMETER_META_BLOCK) {
      r = parameter_meta_block(b, 0);
    }
    else if (t == POSTFIX_QUANTIFIER) {
      r = postfix_quantifier(b, 0);
    }
    else if (t == RUNTIME_BLOCK) {
      r = runtime_block(b, 0);
    }
    else if (t == SCATTER_BLOCK) {
      r = scatter_block(b, 0);
    }
    else if (t == SECTIONS) {
      r = sections(b, 0);
    }
    else if (t == SETTER) {
      r = setter(b, 0);
    }
    else if (t == STRING_LITERAL) {
      r = string_literal(b, 0);
    }
    else if (t == TASK_BLOCK) {
      r = task_block(b, 0);
    }
    else if (t == TASK_OUTPUTS) {
      r = task_outputs(b, 0);
    }
    else if (t == TYPE_E) {
      r = type_e(b, 0);
    }
    else if (t == VALUE) {
      r = value(b, 0);
    }
    else if (t == WF_BODY_ELEMENT) {
      r = wf_body_element(b, 0);
    }
    else if (t == WF_OUTPUT) {
      r = wf_output(b, 0);
    }
    else if (t == WF_OUTPUT_WILDCARD) {
      r = wf_output_wildcard(b, 0);
    }
    else if (t == WF_OUTPUTS) {
      r = wf_outputs(b, 0);
    }
    else if (t == WHILE_LOOP) {
      r = while_loop(b, 0);
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
  // LSQUARE expression (COMMA expression)* RSQUARE
  public static boolean array_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_literal")) return false;
    if (!nextTokenIs(b, LSQUARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LSQUARE);
    r = r && expression(b, l + 1);
    r = r && array_literal_2(b, l + 1);
    r = r && consumeToken(b, RSQUARE);
    exit_section_(b, m, ARRAY_LITERAL, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean array_literal_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_literal_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!array_literal_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "array_literal_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA expression
  private static boolean array_literal_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_literal_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER LSQUARE expression RSQUARE
  public static boolean array_or_map_lookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_or_map_lookup")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, LSQUARE);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RSQUARE);
    exit_section_(b, m, ARRAY_OR_MAP_LOOKUP, r);
    return r;
  }

  /* ********************************************************** */
  // CALL fqn alias? LBRACE call_body RBRACE
  public static boolean call_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_block")) return false;
    if (!nextTokenIs(b, CALL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CALL);
    r = r && fqn(b, l + 1);
    r = r && call_block_2(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && call_body(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, CALL_BLOCK, r);
    return r;
  }

  // alias?
  private static boolean call_block_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_block_2")) return false;
    alias(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // declaration* call_input*
  public static boolean call_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_body")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<call body>");
    r = call_body_0(b, l + 1);
    r = r && call_body_1(b, l + 1);
    exit_section_(b, l, m, CALL_BODY, r, false, null);
    return r;
  }

  // declaration*
  private static boolean call_body_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_body_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "call_body_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // call_input*
  private static boolean call_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_body_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!call_input(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "call_body_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // INPUT COLON (mapping (COMMA mapping)*)?
  public static boolean call_input(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_input")) return false;
    if (!nextTokenIs(b, INPUT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INPUT, COLON);
    r = r && call_input_2(b, l + 1);
    exit_section_(b, m, CALL_INPUT, r);
    return r;
  }

  // (mapping (COMMA mapping)*)?
  private static boolean call_input_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_input_2")) return false;
    call_input_2_0(b, l + 1);
    return true;
  }

  // mapping (COMMA mapping)*
  private static boolean call_input_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_input_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = mapping(b, l + 1);
    r = r && call_input_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA mapping)*
  private static boolean call_input_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_input_2_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!call_input_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "call_input_2_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA mapping
  private static boolean call_input_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_input_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && mapping(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMAND COMMAND_DELIMITER_OPEN (COMMAND_CHAR|command_var)* COMMAND_DELIMITER_CLOSE
  public static boolean command_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_block")) return false;
    if (!nextTokenIs(b, COMMAND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMAND, COMMAND_DELIMITER_OPEN);
    r = r && command_block_2(b, l + 1);
    r = r && consumeToken(b, COMMAND_DELIMITER_CLOSE);
    exit_section_(b, m, COMMAND_BLOCK, r);
    return r;
  }

  // (COMMAND_CHAR|command_var)*
  private static boolean command_block_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_block_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!command_block_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "command_block_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMAND_CHAR|command_var
  private static boolean command_block_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_block_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMAND_CHAR);
    if (!r) r = command_var(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMAND_VAR_OPENER command_var_option? expression RBRACE
  public static boolean command_var(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_var")) return false;
    if (!nextTokenIs(b, COMMAND_VAR_OPENER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMAND_VAR_OPENER);
    r = r && command_var_1(b, l + 1);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, COMMAND_VAR, r);
    return r;
  }

  // command_var_option?
  private static boolean command_var_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_var_1")) return false;
    command_var_option(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SEP EQUAL expression
  public static boolean command_var_option(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_var_option")) return false;
    if (!nextTokenIs(b, SEP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SEP, EQUAL);
    r = r && expression(b, l + 1);
    exit_section_(b, m, COMMAND_VAR_OPTION, r);
    return r;
  }

  /* ********************************************************** */
  // type_e postfix_quantifier? IDENTIFIER setter?
  public static boolean declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_e(b, l + 1);
    r = r && declaration_1(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && declaration_3(b, l + 1);
    exit_section_(b, m, DECLARATION, r);
    return r;
  }

  // postfix_quantifier?
  private static boolean declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration_1")) return false;
    postfix_quantifier(b, l + 1);
    return true;
  }

  // setter?
  private static boolean declaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration_3")) return false;
    setter(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // value expression_dash | LPAREN expression RPAREN expression_dash | LPAREN expression RPAREN |
  //                 LOGICAL_NOT expression | PLUS expression | DASH expression | function_call | array_or_map_lookup |
  //                 member_access | object_literal | map_literal | array_literal
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = expression_0(b, l + 1);
    if (!r) r = expression_1(b, l + 1);
    if (!r) r = expression_2(b, l + 1);
    if (!r) r = expression_3(b, l + 1);
    if (!r) r = expression_4(b, l + 1);
    if (!r) r = expression_5(b, l + 1);
    if (!r) r = function_call(b, l + 1);
    if (!r) r = array_or_map_lookup(b, l + 1);
    if (!r) r = member_access(b, l + 1);
    if (!r) r = object_literal(b, l + 1);
    if (!r) r = map_literal(b, l + 1);
    if (!r) r = array_literal(b, l + 1);
    exit_section_(b, l, m, EXPRESSION, r, false, null);
    return r;
  }

  // value expression_dash
  private static boolean expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = value(b, l + 1);
    r = r && expression_dash(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LPAREN expression RPAREN expression_dash
  private static boolean expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && expression_dash(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LPAREN expression RPAREN
  private static boolean expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // LOGICAL_NOT expression
  private static boolean expression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LOGICAL_NOT);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS expression
  private static boolean expression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DASH expression
  private static boolean expression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DASH);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (infix_operator expression expression_dash)?
  public static boolean expression_dash(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_dash")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<expression dash>");
    expression_dash_0(b, l + 1);
    exit_section_(b, l, m, EXPRESSION_DASH, true, false, null);
    return true;
  }

  // infix_operator expression expression_dash
  private static boolean expression_dash_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_dash_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = infix_operator(b, l + 1);
    r = r && expression(b, l + 1);
    r = r && expression_dash(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER+ DOT NUMBER+
  public static boolean float_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "float_value")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = float_value_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && float_value_2(b, l + 1);
    exit_section_(b, m, FLOAT_VALUE, r);
    return r;
  }

  // NUMBER+
  private static boolean float_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "float_value_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NUMBER)) break;
      if (!empty_element_parsed_guard_(b, "float_value_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMBER+
  private static boolean float_value_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "float_value_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NUMBER)) break;
      if (!empty_element_parsed_guard_(b, "float_value_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (DOT IDENTIFIER)*
  public static boolean fqn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqn")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && fqn_1(b, l + 1);
    exit_section_(b, m, FQN, r);
    return r;
  }

  // (DOT IDENTIFIER)*
  private static boolean fqn_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqn_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!fqn_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fqn_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DOT IDENTIFIER
  private static boolean fqn_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqn_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER LPAREN expression (COMMA expression)* RPAREN
  public static boolean function_call(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, LPAREN);
    r = r && expression(b, l + 1);
    r = r && function_call_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, FUNCTION_CALL, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean function_call_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!function_call_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "function_call_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA expression
  private static boolean function_call_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IF LPAREN expression RPAREN LBRACE wf_body_element* RBRACE
  public static boolean if_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_stmt")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, LPAREN);
    r = r && expression(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, LBRACE);
    r = r && if_stmt_5(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, IF_STMT, r);
    return r;
  }

  // wf_body_element*
  private static boolean if_stmt_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_stmt_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!wf_body_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "if_stmt_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // AS IDENTIFIER
  public static boolean import_namespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_namespace")) return false;
    if (!nextTokenIs(b, AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AS, IDENTIFIER);
    exit_section_(b, m, IMPORT_NAMESPACE, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT string_literal import_namespace?
  public static boolean import_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_stmt")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && string_literal(b, l + 1);
    r = r && import_stmt_2(b, l + 1);
    exit_section_(b, m, IMPORT_STMT, r);
    return r;
  }

  // import_namespace?
  private static boolean import_stmt_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_stmt_2")) return false;
    import_namespace(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DOUBLE_PIPE | DOUBLE_AMPERSAND | DOUBLE_EQUAL | NOT_EQUAL | LESS_THAN | LESS_EQUAL | MORE_THAN |
  //                     MORE_EQUAL | PLUS | DASH | ASTERISK | SLASH | PERCENT
  public static boolean infix_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infix_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<infix operator>");
    r = consumeToken(b, DOUBLE_PIPE);
    if (!r) r = consumeToken(b, DOUBLE_AMPERSAND);
    if (!r) r = consumeToken(b, DOUBLE_EQUAL);
    if (!r) r = consumeToken(b, NOT_EQUAL);
    if (!r) r = consumeToken(b, LESS_THAN);
    if (!r) r = consumeToken(b, LESS_EQUAL);
    if (!r) r = consumeToken(b, MORE_THAN);
    if (!r) r = consumeToken(b, MORE_EQUAL);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, DASH);
    if (!r) r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, PERCENT);
    exit_section_(b, l, m, INFIX_OPERATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NUMBER+
  public static boolean integer_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer_value")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NUMBER)) break;
      if (!empty_element_parsed_guard_(b, "integer_value", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, INTEGER_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON expression
  public static boolean kv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kv")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && expression(b, l + 1);
    exit_section_(b, m, KV, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE kv* RBRACE
  public static boolean map(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && map_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, MAP, r);
    return r;
  }

  // kv*
  private static boolean map_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!kv(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "map_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // expression COLON expression
  public static boolean map_kv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_kv")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<map kv>");
    r = expression(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && expression(b, l + 1);
    exit_section_(b, l, m, MAP_KV, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBRACE map_kv (COMMA map_kv)* RBRACE
  public static boolean map_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_literal")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && map_kv(b, l + 1);
    r = r && map_literal_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, MAP_LITERAL, r);
    return r;
  }

  // (COMMA map_kv)*
  private static boolean map_literal_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_literal_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!map_literal_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "map_literal_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA map_kv
  private static boolean map_literal_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_literal_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && map_kv(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUAL expression
  public static boolean mapping(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQUAL);
    r = r && expression(b, l + 1);
    exit_section_(b, m, MAPPING, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER DOT IDENTIFIER
  public static boolean member_access(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_access")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, DOT, IDENTIFIER);
    exit_section_(b, m, MEMBER_ACCESS, r);
    return r;
  }

  /* ********************************************************** */
  // META map
  public static boolean meta_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "meta_block")) return false;
    if (!nextTokenIs(b, META)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, META);
    r = r && map(b, l + 1);
    exit_section_(b, m, META_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // OBJECT LBRACE map_kv (COMMA map_kv)* RBRACE
  public static boolean object_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_literal")) return false;
    if (!nextTokenIs(b, OBJECT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OBJECT, LBRACE);
    r = r && map_kv(b, l + 1);
    r = r && object_literal_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, OBJECT_LITERAL, r);
    return r;
  }

  // (COMMA map_kv)*
  private static boolean object_literal_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_literal_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!object_literal_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_literal_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA map_kv
  private static boolean object_literal_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_literal_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && map_kv(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // type_e IDENTIFIER EQUAL expression
  public static boolean output_kv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "output_kv")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_e(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, EQUAL);
    r = r && expression(b, l + 1);
    exit_section_(b, m, OUTPUT_KV, r);
    return r;
  }

  /* ********************************************************** */
  // PARAMETER_META map
  public static boolean parameter_meta_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_meta_block")) return false;
    if (!nextTokenIs(b, PARAMETER_META)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARAMETER_META);
    r = r && map(b, l + 1);
    exit_section_(b, m, PARAMETER_META_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // QMARK | PLUS
  public static boolean postfix_quantifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "postfix_quantifier")) return false;
    if (!nextTokenIs(b, "<postfix quantifier>", PLUS, QMARK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<postfix quantifier>");
    r = consumeToken(b, QMARK);
    if (!r) r = consumeToken(b, PLUS);
    exit_section_(b, l, m, POSTFIX_QUANTIFIER, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // RUNTIME map
  public static boolean runtime_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "runtime_block")) return false;
    if (!nextTokenIs(b, RUNTIME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RUNTIME);
    r = r && map(b, l + 1);
    exit_section_(b, m, RUNTIME_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // SCATTER LPAREN IDENTIFIER IN expression RPAREN LBRACE wf_body_element* RBRACE
  public static boolean scatter_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scatter_block")) return false;
    if (!nextTokenIs(b, SCATTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SCATTER, LPAREN, IDENTIFIER, IN);
    r = r && expression(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, LBRACE);
    r = r && scatter_block_7(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SCATTER_BLOCK, r);
    return r;
  }

  // wf_body_element*
  private static boolean scatter_block_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scatter_block_7")) return false;
    int c = current_position_(b);
    while (true) {
      if (!wf_body_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "scatter_block_7", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // command_block|task_outputs|runtime_block|parameter_meta_block|meta_block
  public static boolean sections(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sections")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<sections>");
    r = command_block(b, l + 1);
    if (!r) r = task_outputs(b, l + 1);
    if (!r) r = runtime_block(b, l + 1);
    if (!r) r = parameter_meta_block(b, l + 1);
    if (!r) r = meta_block(b, l + 1);
    exit_section_(b, l, m, SECTIONS, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EQUAL expression
  public static boolean setter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "setter")) return false;
    if (!nextTokenIs(b, EQUAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL);
    r = r && expression(b, l + 1);
    exit_section_(b, m, SETTER, r);
    return r;
  }

  /* ********************************************************** */
  // QUOTE (QUOTE_STRING_CHAR|ESCAPE_SEQUENCE)* QUOTE
  public static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    if (!nextTokenIs(b, QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE);
    r = r && string_literal_1(b, l + 1);
    r = r && consumeToken(b, QUOTE);
    exit_section_(b, m, STRING_LITERAL, r);
    return r;
  }

  // (QUOTE_STRING_CHAR|ESCAPE_SEQUENCE)*
  private static boolean string_literal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!string_literal_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "string_literal_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // QUOTE_STRING_CHAR|ESCAPE_SEQUENCE
  private static boolean string_literal_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE_STRING_CHAR);
    if (!r) r = consumeToken(b, ESCAPE_SEQUENCE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TASK TASK_IDENTIFIER_DECL LBRACE declaration* sections* RBRACE
  public static boolean task_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_block")) return false;
    if (!nextTokenIs(b, TASK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TASK, TASK_IDENTIFIER_DECL, LBRACE);
    r = r && task_block_3(b, l + 1);
    r = r && task_block_4(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, TASK_BLOCK, r);
    return r;
  }

  // declaration*
  private static boolean task_block_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_block_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "task_block_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // sections*
  private static boolean task_block_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_block_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!sections(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "task_block_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // OUTPUT LBRACE output_kv* RBRACE
  public static boolean task_outputs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_outputs")) return false;
    if (!nextTokenIs(b, OUTPUT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OUTPUT, LBRACE);
    r = r && task_outputs_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, TASK_OUTPUTS, r);
    return r;
  }

  // output_kv*
  private static boolean task_outputs_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_outputs_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!output_kv(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "task_outputs_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // TYPE (LSQUARE type_e (COMMA type_e)* RSQUARE)?
  public static boolean type_e(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_e")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TYPE);
    r = r && type_e_1(b, l + 1);
    exit_section_(b, m, TYPE_E, r);
    return r;
  }

  // (LSQUARE type_e (COMMA type_e)* RSQUARE)?
  private static boolean type_e_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_e_1")) return false;
    type_e_1_0(b, l + 1);
    return true;
  }

  // LSQUARE type_e (COMMA type_e)* RSQUARE
  private static boolean type_e_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_e_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LSQUARE);
    r = r && type_e(b, l + 1);
    r = r && type_e_1_0_2(b, l + 1);
    r = r && consumeToken(b, RSQUARE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA type_e)*
  private static boolean type_e_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_e_1_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!type_e_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "type_e_1_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA type_e
  private static boolean type_e_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_e_1_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && type_e(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // string_literal | IDENTIFIER | BOOLEAN | float_value | integer_value
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<value>");
    r = string_literal(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = float_value(b, l + 1);
    if (!r) r = integer_value(b, l + 1);
    exit_section_(b, l, m, VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // import_stmt* (task_block|workflow_block|COMMENT)*
  static boolean wdlFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wdlFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = wdlFile_0(b, l + 1);
    r = r && wdlFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // import_stmt*
  private static boolean wdlFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wdlFile_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!import_stmt(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "wdlFile_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (task_block|workflow_block|COMMENT)*
  private static boolean wdlFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wdlFile_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!wdlFile_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "wdlFile_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // task_block|workflow_block|COMMENT
  private static boolean wdlFile_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wdlFile_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = task_block(b, l + 1);
    if (!r) r = workflow_block(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // call_block | declaration | while_loop | if_stmt | scatter_block | wf_outputs
  public static boolean wf_body_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_body_element")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<wf body element>");
    r = call_block(b, l + 1);
    if (!r) r = declaration(b, l + 1);
    if (!r) r = while_loop(b, l + 1);
    if (!r) r = if_stmt(b, l + 1);
    if (!r) r = scatter_block(b, l + 1);
    if (!r) r = wf_outputs(b, l + 1);
    exit_section_(b, l, m, WF_BODY_ELEMENT, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // fqn wf_output_wildcard?
  public static boolean wf_output(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_output")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fqn(b, l + 1);
    r = r && wf_output_1(b, l + 1);
    exit_section_(b, m, WF_OUTPUT, r);
    return r;
  }

  // wf_output_wildcard?
  private static boolean wf_output_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_output_1")) return false;
    wf_output_wildcard(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DOT ASTERISK
  public static boolean wf_output_wildcard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_output_wildcard")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, ASTERISK);
    exit_section_(b, m, WF_OUTPUT_WILDCARD, r);
    return r;
  }

  /* ********************************************************** */
  // OUTPUT LBRACE wf_output* RBRACE
  public static boolean wf_outputs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_outputs")) return false;
    if (!nextTokenIs(b, OUTPUT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OUTPUT, LBRACE);
    r = r && wf_outputs_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, WF_OUTPUTS, r);
    return r;
  }

  // wf_output*
  private static boolean wf_outputs_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wf_outputs_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!wf_output(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "wf_outputs_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // WHILE LPAREN expression RPAREN LBRACE wf_body_element* RBRACE
  public static boolean while_loop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_loop")) return false;
    if (!nextTokenIs(b, WHILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, WHILE, LPAREN);
    r = r && expression(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, LBRACE);
    r = r && while_loop_5(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, WHILE_LOOP, r);
    return r;
  }

  // wf_body_element*
  private static boolean while_loop_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_loop_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!wf_body_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "while_loop_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // WORKFLOW WORKFLOW_IDENTIFIER_DECL LBRACE wf_body_element* RBRACE
  public static boolean workflow_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "workflow_block")) return false;
    if (!nextTokenIs(b, WORKFLOW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, WORKFLOW, WORKFLOW_IDENTIFIER_DECL, LBRACE);
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
