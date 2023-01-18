package org.broadinstitute.winstanley.psi

import com.intellij.psi.PsiNameIdentifierOwner

trait WdlNamedTaskElement extends PsiNameIdentifierOwner {
  def declaredValueName: Option[String]
}
