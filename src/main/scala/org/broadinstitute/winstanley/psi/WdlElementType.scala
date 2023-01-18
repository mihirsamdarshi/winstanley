package org.broadinstitute.winstanley.psi

import com.intellij.psi.tree.IElementType
import org.broadinstitute.winstanley.WdlLanguage
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.NotNull

class WdlElementType(@NotNull @NonNls debugName: String) extends IElementType(debugName, WdlLanguage.INSTANCE)