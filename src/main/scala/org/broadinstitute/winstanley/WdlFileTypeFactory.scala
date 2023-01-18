package org.broadinstitute.winstanley

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory
import org.jetbrains.annotations.NotNull

class WdlFileTypeFactory extends FileTypeFactory {

  override def createFileTypes(@NotNull fileTypeConsumer: FileTypeConsumer) =
    fileTypeConsumer.consume(WdlFileType.INSTANCE, "wdl")
}