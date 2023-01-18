package org.broadinstitute.winstanley

import com.intellij.lang.Commenter

/**
  * Enables automated commenting/uncommenting of blocks of WDL code
  */
class WdlCommenter extends Commenter {

  // WDL comments begin with a #:
  override def getLineCommentPrefix: String = "#"

  // Every other comment type is not supported in WDL:
  override def getBlockCommentSuffix: String = null
  override def getCommentedBlockCommentSuffix: String = null
  override def getBlockCommentPrefix: String = null
  override def getCommentedBlockCommentPrefix: String = null
}
