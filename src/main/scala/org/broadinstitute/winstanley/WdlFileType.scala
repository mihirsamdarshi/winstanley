package org.broadinstitute.winstanley

import com.intellij.openapi.fileTypes.LanguageFileType
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import javax.swing._

object WdlFileType {
  val INSTANCE = new WdlFileType()
}

class WdlFileType extends LanguageFileType(WdlLanguage.INSTANCE) {

  @NotNull
  override def getName: String = "WDL file"

  @NotNull
  override def getDescription: String = "WDL language file"

  @NotNull
  override def getDefaultExtension: String = "wdl"

  @Nullable
  override def getIcon: Icon = WdlIcons.FILE
}