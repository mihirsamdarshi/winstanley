package winstanley.psi.impl

import com.intellij.psi.{PsiElement, PsiReference}
import winstanley.psi.{WdlCallableLookup, WdlValueLookup}
import winstanley.references.{WdlCallableDeclarationReference, WdlReference}
import winstanley.structure.WdlImplicits._

/** This class is used by the .bnf compiler to implement the 'methods=[...]' methods on PsiElements.
  *
  * Put all your other junk util methods somewhere else!
  */
object WdlPsiImplUtil
    extends WdlNamedElementImplUtil
    with WdlNamedTaskElementImplUtil
    with WdlValueLookupImplUtil
    with WdlCallableLookupImplUtil
    with WdlCallAliasImplUtil

/** Provides the getName, setName and getNameIdentifier methods for the WdlNamedElement subclasses (see (eg) declaration and scatter_declaration in the bnf)
  */
sealed trait WdlNamedElementImplUtil {
  def getName(namedElement: WdlNamedElementImpl): String =
    namedElement.declaredValueName.orNull

  // TODO: Implement for "refactor/rename" functionality
  def setName(namedElement: WdlNamedElementImpl, newName: String): PsiElement =
    ???

  def getNameIdentifier(namedElement: WdlNamedElementImpl): PsiElement =
    namedElement match {
      case callable: WdlCallableLookupImpl =>
        callable.getFullyQualifiedName.getLastChild
      case _ => namedElement.getIdentifierNode.map(_.getPsi).orNull
    }
}

sealed trait WdlNamedTaskElementImplUtil {
  def getName(namedTaskElement: WdlNamedTaskElementImpl): String =
    namedTaskElement.declaredValueName.orNull
  def getNameIdentifier(namedTaskElement: WdlNamedTaskElementImpl): PsiElement =
    namedTaskElement.getTaskIdentifierNode.map(_.getPsi).orNull
}

sealed trait WdlValueLookupImplUtil {

  /** This is the method that enables the 'go to declaration' functionality for variable usages.
    */
  def getReferences(wdlValueLookup: WdlValueLookup): Array[PsiReference] =
    Array(WdlReference(wdlValueLookup))
}

sealed trait WdlCallableLookupImplUtil {

  /** This is the method that enables the 'go to declaration' functionality for task usages.
    */
  def getNameIdentifier(callableLookup: WdlCallableLookupImpl): PsiElement =
    callableLookup.getFullyQualifiedName.getLastChild
  def getReferences(wdlCallableLookup: WdlCallableLookup): Array[PsiReference] =
    Array(WdlCallableDeclarationReference(wdlCallableLookup))
}

sealed trait WdlCallAliasImplUtil {
  def getName(callAlias: WdlCallAliasImpl): String =
    callAlias.getIdentifierNode.map(_.getText).orNull

  def getNameIdentifier(callAlias: WdlCallAliasImpl): PsiElement =
    callAlias.getIdentifierNode.map(_.getPsi).orNull
}
