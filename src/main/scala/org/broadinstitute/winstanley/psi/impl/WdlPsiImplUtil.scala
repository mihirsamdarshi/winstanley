package org.broadinstitute.winstanley.psi.impl

import com.intellij.psi.{PsiElement, PsiReference}
import org.broadinstitute.winstanley.psi.{WdlCallableLookup, WdlValueLookup}
import org.broadinstitute.winstanley.references.{WdlCallableDeclarationReference, WdlReference}
import org.broadinstitute.winstanley.structure.WdlImplicits._


/**
  * This class is used by the .bnf compiler to implement the 'methods=[...]' methods on PsiElements.
  *
  * Put all your other junk util methods somewhere else!
  */
object WdlPsiImplUtil extends
  WdlNamedElementImplUtil with
  WdlNamedTaskElementImplUtil with
  WdlValueLookupImplUtil with
  WdlCallableLookupImplUtil

/**
  * Provides the getName, setName and getNameIdentifier methods for the WdlNamedElement subclasses (see (eg) declaration and scatter_declaration in the bnf)
  */
sealed trait WdlNamedElementImplUtil {
  def getName(namedElement: WdlNamedElementImpl): String = namedElement.declaredValueName.orNull
  // TODO: Implement for "refactor/rename" functionality
  def setName(namedElement: WdlNamedElementImpl, newName: String): PsiElement = ???
  def getNameIdentifier(namedElement: WdlNamedElementImpl): PsiElement = namedElement match {
    case callable: WdlCallableLookupImpl => callable.getFullyQualifiedName.getLastChild
    case _ => namedElement.getIdentifierNode.map(_.getPsi).orNull
  }
}

sealed trait WdlNamedTaskElementImplUtil {
  def getName(namedTaskElement: WdlNamedTaskElementImpl): String = namedTaskElement.declaredValueName.orNull
  // TODO: Implement for "refactor/rename" functionality
  def setName(namedTaskElement: WdlNamedTaskElementImpl, newName: String): PsiElement = ???
  def getNameIdentifier(namedTaskElement: WdlNamedTaskElementImpl): PsiElement = namedTaskElement.getTaskIdentifierNode.map(_.getPsi).orNull
}

sealed trait WdlValueLookupImplUtil {
  /**
    * This is the method that enables the 'go to declaration' functionality for variable usages.
    */
  def getReferences(wdlValueLookup: WdlValueLookup): Array[PsiReference] = Array(WdlReference(wdlValueLookup))
}

sealed trait WdlCallableLookupImplUtil {
  /**
    * This is the method that enables the 'go to declaration' functionality for task usages.
    */
  def getReferences(wdlCallableLookup: WdlCallableLookup): Array[PsiReference] = Array(WdlCallableDeclarationReference(wdlCallableLookup))
}
