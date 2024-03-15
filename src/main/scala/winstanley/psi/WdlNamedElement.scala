package winstanley.psi

import com.intellij.psi.PsiNameIdentifierOwner

trait WdlNamedElement extends PsiNameIdentifierOwner {
  def declaredValueName: Option[String]
}
