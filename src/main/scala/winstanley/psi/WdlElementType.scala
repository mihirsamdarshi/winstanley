package winstanley.psi

import com.intellij.psi.tree.IElementType
import winstanley.WdlLanguage
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.NotNull

class WdlElementType(@NotNull @NonNls debugName: String) extends IElementType(debugName, WdlLanguage.INSTANCE)