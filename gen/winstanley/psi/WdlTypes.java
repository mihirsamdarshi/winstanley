// This is a generated file. Not intended for manual editing.
package winstanley.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import winstanley.psi.impl.*;

public interface WdlTypes {

  IElementType ALIAS = new WdlElementType("ALIAS");
  IElementType ARRAY_LITERAL = new WdlElementType("ARRAY_LITERAL");
  IElementType ARRAY_OR_MAP_LOOKUP = new WdlElementType("ARRAY_OR_MAP_LOOKUP");
  IElementType CALL_BLOCK = new WdlElementType("CALL_BLOCK");
  IElementType CALL_BODY = new WdlElementType("CALL_BODY");
  IElementType CALL_INPUT = new WdlElementType("CALL_INPUT");
  IElementType COMMAND_BLOCK = new WdlElementType("COMMAND_BLOCK");
  IElementType COMMAND_VAR = new WdlElementType("COMMAND_VAR");
  IElementType COMMAND_VAR_OPTION = new WdlElementType("COMMAND_VAR_OPTION");
  IElementType DECLARATION = new WdlElementType("DECLARATION");
  IElementType EXPRESSION = new WdlElementType("EXPRESSION");
  IElementType EXPRESSION_DASH = new WdlElementType("EXPRESSION_DASH");
  IElementType FLOAT_VALUE = new WdlElementType("FLOAT_VALUE");
  IElementType FQN = new WdlElementType("FQN");
  IElementType FUNCTION_CALL = new WdlElementType("FUNCTION_CALL");
  IElementType IF_STMT = new WdlElementType("IF_STMT");
  IElementType IMPORT_NAMESPACE = new WdlElementType("IMPORT_NAMESPACE");
  IElementType IMPORT_STMT = new WdlElementType("IMPORT_STMT");
  IElementType INFIX_OPERATOR = new WdlElementType("INFIX_OPERATOR");
  IElementType INTEGER_VALUE = new WdlElementType("INTEGER_VALUE");
  IElementType KV = new WdlElementType("KV");
  IElementType MAP = new WdlElementType("MAP");
  IElementType MAPPING = new WdlElementType("MAPPING");
  IElementType MAP_KV = new WdlElementType("MAP_KV");
  IElementType MAP_LITERAL = new WdlElementType("MAP_LITERAL");
  IElementType MEMBER_ACCESS = new WdlElementType("MEMBER_ACCESS");
  IElementType META_BLOCK = new WdlElementType("META_BLOCK");
  IElementType OBJECT_LITERAL = new WdlElementType("OBJECT_LITERAL");
  IElementType OUTPUT_KV = new WdlElementType("OUTPUT_KV");
  IElementType PARAMETER_META_BLOCK = new WdlElementType("PARAMETER_META_BLOCK");
  IElementType POSTFIX_QUANTIFIER = new WdlElementType("POSTFIX_QUANTIFIER");
  IElementType RUNTIME_BLOCK = new WdlElementType("RUNTIME_BLOCK");
  IElementType SCATTER_BLOCK = new WdlElementType("SCATTER_BLOCK");
  IElementType SECTIONS = new WdlElementType("SECTIONS");
  IElementType SETTER = new WdlElementType("SETTER");
  IElementType STRING_LITERAL = new WdlElementType("STRING_LITERAL");
  IElementType TASK_BLOCK = new WdlElementType("TASK_BLOCK");
  IElementType TASK_OUTPUTS = new WdlElementType("TASK_OUTPUTS");
  IElementType TYPE_E = new WdlElementType("TYPE_E");
  IElementType VALUE = new WdlElementType("VALUE");
  IElementType WF_BODY_ELEMENT = new WdlElementType("WF_BODY_ELEMENT");
  IElementType WF_OUTPUT = new WdlElementType("WF_OUTPUT");
  IElementType WF_OUTPUTS = new WdlElementType("WF_OUTPUTS");
  IElementType WF_OUTPUT_WILDCARD = new WdlElementType("WF_OUTPUT_WILDCARD");
  IElementType WHILE_LOOP = new WdlElementType("WHILE_LOOP");
  IElementType WORKFLOW_BLOCK = new WdlElementType("WORKFLOW_BLOCK");

  IElementType AS = new WdlTokenType("AS");
  IElementType ASTERISK = new WdlTokenType("ASTERISK");
  IElementType BOOLEAN = new WdlTokenType("BOOLEAN");
  IElementType CALL = new WdlTokenType("CALL");
  IElementType COLON = new WdlTokenType("COLON");
  IElementType COMMA = new WdlTokenType("COMMA");
  IElementType COMMAND = new WdlTokenType("COMMAND");
  IElementType COMMAND_CHAR = new WdlTokenType("COMMAND_CHAR");
  IElementType COMMAND_DELIMITER_CLOSE = new WdlTokenType("COMMAND_DELIMITER_CLOSE");
  IElementType COMMAND_DELIMITER_OPEN = new WdlTokenType("COMMAND_DELIMITER_OPEN");
  IElementType COMMAND_VAR_OPENER = new WdlTokenType("COMMAND_VAR_OPENER");
  IElementType COMMENT = new WdlTokenType("COMMENT");
  IElementType DASH = new WdlTokenType("DASH");
  IElementType DOT = new WdlTokenType("DOT");
  IElementType DOUBLE_AMPERSAND = new WdlTokenType("DOUBLE_AMPERSAND");
  IElementType DOUBLE_EQUAL = new WdlTokenType("DOUBLE_EQUAL");
  IElementType DOUBLE_PIPE = new WdlTokenType("DOUBLE_PIPE");
  IElementType EQUAL = new WdlTokenType("EQUAL");
  IElementType ESCAPE_SEQUENCE = new WdlTokenType("ESCAPE_SEQUENCE");
  IElementType IDENTIFIER = new WdlTokenType("IDENTIFIER");
  IElementType IF = new WdlTokenType("IF");
  IElementType IMPORT = new WdlTokenType("IMPORT");
  IElementType IN = new WdlTokenType("IN");
  IElementType INPUT = new WdlTokenType("INPUT");
  IElementType LBRACE = new WdlTokenType("LBRACE");
  IElementType LESS_EQUAL = new WdlTokenType("LESS_EQUAL");
  IElementType LESS_THAN = new WdlTokenType("LESS_THAN");
  IElementType LOGICAL_NOT = new WdlTokenType("LOGICAL_NOT");
  IElementType LPAREN = new WdlTokenType("LPAREN");
  IElementType LSQUARE = new WdlTokenType("LSQUARE");
  IElementType META = new WdlTokenType("META");
  IElementType MORE_EQUAL = new WdlTokenType("MORE_EQUAL");
  IElementType MORE_THAN = new WdlTokenType("MORE_THAN");
  IElementType NOT_EQUAL = new WdlTokenType("NOT_EQUAL");
  IElementType NUMBER = new WdlTokenType("NUMBER");
  IElementType OBJECT = new WdlTokenType("OBJECT");
  IElementType OUTPUT = new WdlTokenType("OUTPUT");
  IElementType PARAMETER_META = new WdlTokenType("PARAMETER_META");
  IElementType PERCENT = new WdlTokenType("PERCENT");
  IElementType PLUS = new WdlTokenType("PLUS");
  IElementType QMARK = new WdlTokenType("QMARK");
  IElementType QUOTE = new WdlTokenType("QUOTE");
  IElementType QUOTE_STRING_CHAR = new WdlTokenType("QUOTE_STRING_CHAR");
  IElementType RBRACE = new WdlTokenType("RBRACE");
  IElementType RPAREN = new WdlTokenType("RPAREN");
  IElementType RSQUARE = new WdlTokenType("RSQUARE");
  IElementType RUNTIME = new WdlTokenType("RUNTIME");
  IElementType SCATTER = new WdlTokenType("SCATTER");
  IElementType SEP = new WdlTokenType("SEP");
  IElementType SLASH = new WdlTokenType("SLASH");
  IElementType TASK = new WdlTokenType("TASK");
  IElementType TASK_IDENTIFIER_DECL = new WdlTokenType("TASK_IDENTIFIER_DECL");
  IElementType TYPE = new WdlTokenType("TYPE");
  IElementType WHILE = new WdlTokenType("WHILE");
  IElementType WORKFLOW = new WdlTokenType("WORKFLOW");
  IElementType WORKFLOW_IDENTIFIER_DECL = new WdlTokenType("WORKFLOW_IDENTIFIER_DECL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ALIAS) {
        return new WdlAliasImpl(node);
      }
      else if (type == ARRAY_LITERAL) {
        return new WdlArrayLiteralImpl(node);
      }
      else if (type == ARRAY_OR_MAP_LOOKUP) {
        return new WdlArrayOrMapLookupImpl(node);
      }
      else if (type == CALL_BLOCK) {
        return new WdlCallBlockImpl(node);
      }
      else if (type == CALL_BODY) {
        return new WdlCallBodyImpl(node);
      }
      else if (type == CALL_INPUT) {
        return new WdlCallInputImpl(node);
      }
      else if (type == COMMAND_BLOCK) {
        return new WdlCommandBlockImpl(node);
      }
      else if (type == COMMAND_VAR) {
        return new WdlCommandVarImpl(node);
      }
      else if (type == COMMAND_VAR_OPTION) {
        return new WdlCommandVarOptionImpl(node);
      }
      else if (type == DECLARATION) {
        return new WdlDeclarationImpl(node);
      }
      else if (type == EXPRESSION) {
        return new WdlExpressionImpl(node);
      }
      else if (type == EXPRESSION_DASH) {
        return new WdlExpressionDashImpl(node);
      }
      else if (type == FLOAT_VALUE) {
        return new WdlFloatValueImpl(node);
      }
      else if (type == FQN) {
        return new WdlFqnImpl(node);
      }
      else if (type == FUNCTION_CALL) {
        return new WdlFunctionCallImpl(node);
      }
      else if (type == IF_STMT) {
        return new WdlIfStmtImpl(node);
      }
      else if (type == IMPORT_NAMESPACE) {
        return new WdlImportNamespaceImpl(node);
      }
      else if (type == IMPORT_STMT) {
        return new WdlImportStmtImpl(node);
      }
      else if (type == INFIX_OPERATOR) {
        return new WdlInfixOperatorImpl(node);
      }
      else if (type == INTEGER_VALUE) {
        return new WdlIntegerValueImpl(node);
      }
      else if (type == KV) {
        return new WdlKvImpl(node);
      }
      else if (type == MAP) {
        return new WdlMapImpl(node);
      }
      else if (type == MAPPING) {
        return new WdlMappingImpl(node);
      }
      else if (type == MAP_KV) {
        return new WdlMapKvImpl(node);
      }
      else if (type == MAP_LITERAL) {
        return new WdlMapLiteralImpl(node);
      }
      else if (type == MEMBER_ACCESS) {
        return new WdlMemberAccessImpl(node);
      }
      else if (type == META_BLOCK) {
        return new WdlMetaBlockImpl(node);
      }
      else if (type == OBJECT_LITERAL) {
        return new WdlObjectLiteralImpl(node);
      }
      else if (type == OUTPUT_KV) {
        return new WdlOutputKvImpl(node);
      }
      else if (type == PARAMETER_META_BLOCK) {
        return new WdlParameterMetaBlockImpl(node);
      }
      else if (type == POSTFIX_QUANTIFIER) {
        return new WdlPostfixQuantifierImpl(node);
      }
      else if (type == RUNTIME_BLOCK) {
        return new WdlRuntimeBlockImpl(node);
      }
      else if (type == SCATTER_BLOCK) {
        return new WdlScatterBlockImpl(node);
      }
      else if (type == SECTIONS) {
        return new WdlSectionsImpl(node);
      }
      else if (type == SETTER) {
        return new WdlSetterImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new WdlStringLiteralImpl(node);
      }
      else if (type == TASK_BLOCK) {
        return new WdlTaskBlockImpl(node);
      }
      else if (type == TASK_OUTPUTS) {
        return new WdlTaskOutputsImpl(node);
      }
      else if (type == TYPE_E) {
        return new WdlTypeEImpl(node);
      }
      else if (type == VALUE) {
        return new WdlValueImpl(node);
      }
      else if (type == WF_BODY_ELEMENT) {
        return new WdlWfBodyElementImpl(node);
      }
      else if (type == WF_OUTPUT) {
        return new WdlWfOutputImpl(node);
      }
      else if (type == WF_OUTPUTS) {
        return new WdlWfOutputsImpl(node);
      }
      else if (type == WF_OUTPUT_WILDCARD) {
        return new WdlWfOutputWildcardImpl(node);
      }
      else if (type == WHILE_LOOP) {
        return new WdlWhileLoopImpl(node);
      }
      else if (type == WORKFLOW_BLOCK) {
        return new WdlWorkflowBlockImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
