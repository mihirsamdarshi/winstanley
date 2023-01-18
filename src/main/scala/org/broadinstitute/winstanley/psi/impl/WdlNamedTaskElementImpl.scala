package org.broadinstitute.winstanley.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import org.broadinstitute.winstanley.structure.WdlImplicits._
import org.broadinstitute.winstanley.psi.WdlNamedTaskElement

abstract class WdlNamedTaskElementImpl(astNode: ASTNode) extends ASTWrapperPsiElement(astNode) with WdlNamedTaskElement {
  // The Option-ality is a little paranoid, but if the declaration is being edited it might be temporarily nameless:
  override def declaredValueName: Option[String] = astNode.getPsi.getTaskIdentifierNode.map(_.getText)
}
