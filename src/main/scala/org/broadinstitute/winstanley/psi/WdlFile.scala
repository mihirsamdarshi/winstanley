package org.broadinstitute.winstanley.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.broadinstitute.winstanley.WdlFileType
import org.broadinstitute.winstanley.WdlLanguage
import org.jetbrains.annotations.NotNull

import javax.swing._

class WdlFile(@NotNull viewProvider: FileViewProvider) extends PsiFileBase(viewProvider, WdlLanguage.INSTANCE) {

  @NotNull
  override def getFileType: FileType = WdlFileType.INSTANCE

  override def toString: String = "WDL File"

  override def getIcon(flags: Int): Icon = super.getIcon(flags)
}