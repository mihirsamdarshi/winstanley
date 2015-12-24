package winstanley

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import winstanley.psi.WdlTypes
import org.jetbrains.annotations.NotNull

import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

object WdlSyntaxHighlighter {
  val SEPARATOR: TextAttributesKey = createTextAttributesKey("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
  val KEY: TextAttributesKey = createTextAttributesKey("SIMPLE_KEY", DefaultLanguageHighlighterColors.KEYWORD)
  val VALUE: TextAttributesKey = createTextAttributesKey("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING)
  val COMMENT: TextAttributesKey  = createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
  val BAD_CHARACTER: TextAttributesKey = createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

  val BAD_CHAR_KEYS: Array[TextAttributesKey] = Seq(BAD_CHARACTER).toArray
  val SEPARATOR_KEYS: Array[TextAttributesKey]  = Seq(SEPARATOR).toArray
  val KEY_KEYS: Array[TextAttributesKey]  = Seq(KEY).toArray
  val VALUE_KEYS: Array[TextAttributesKey]  = Seq(VALUE).toArray
  val COMMENT_KEYS: Array[TextAttributesKey]  = Seq(COMMENT).toArray
  val EMPTY_KEYS: Array[TextAttributesKey] = Seq.empty[TextAttributesKey].toArray
}

class WdlSyntaxHighlighter extends SyntaxHighlighterBase {
  import WdlSyntaxHighlighter._
  @NotNull
  override def getHighlightingLexer: Lexer = new WdlLexerAdapter()

  @NotNull
  override def getTokenHighlights(tokenType: IElementType): Array[TextAttributesKey] = {
    tokenType match {
      case WdlTypes.SEPARATOR => SEPARATOR_KEYS
      case WdlTypes.KEY => KEY_KEYS
      case WdlTypes.VALUE => VALUE_KEYS
      case WdlTypes.COMMENT => COMMENT_KEYS
      case TokenType.BAD_CHARACTER => BAD_CHAR_KEYS
      case _ => EMPTY_KEYS
    }
  }
}