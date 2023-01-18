package org.broadinstitute.winstanley.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import org.broadinstitute.winstanley.structure.WdlImplicits._
import org.broadinstitute.winstanley.psi.{WdlCallableLookup, WdlNamedElement}

abstract class WdlNamedElementImpl(astNode: ASTNode) extends ASTWrapperPsiElement(astNode) with WdlNamedElement {
  // The Option-ality is a little paranoid, but if the declaration is being edited it might be temporarily nameless:
  override def declaredValueName: Option[String] = astNode.getPsi match {
    case callable: WdlCallableLookup => callable.getLastChild.getIdentifierNode.map(_.getText)
    case other => other.getIdentifierNode.map(_.getText)
  }
}
