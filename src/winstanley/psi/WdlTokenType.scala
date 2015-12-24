package winstanley.psi

import com.intellij.psi.tree.IElementType

import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.NotNull

import winstanley.WdlLanguage

class WdlTokenType(@NotNull @NonNls debugName: String) extends IElementType(debugName, WdlLanguage.INSTANCE) {
  override def toString = "WdlTokenType." + super.toString
}