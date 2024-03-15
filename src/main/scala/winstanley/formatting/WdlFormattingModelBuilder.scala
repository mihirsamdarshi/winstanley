package winstanley.formatting

import com.intellij.formatting._
import org.jetbrains.annotations._
import winstanley.WdlLanguage

class WdlFormattingModelBuilder extends FormattingModelBuilder {

  @NotNull
  override def createModel(@NotNull formattingContext: FormattingContext): FormattingModel = {
    val element = formattingContext.getPsiElement
    val settings = formattingContext.getCodeStyleSettings
    FormattingModelProvider.createFormattingModelForPsiFile(
      element.getContainingFile,
      new WdlBlock(
        element.getNode,
        Wrap.createWrap(WrapType.NONE, false),
        Alignment.createAlignment,
        new WdlSpacingBuilder(settings, WdlLanguage.INSTANCE),
        Indent.getNoneIndent
      ),
      settings
    )
  }
}
