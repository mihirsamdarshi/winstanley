package winstanley.psi.impl

import com.intellij.psi.{PsiElement, PsiReference}
import winstanley.psi.{WdlCallableLookup, WdlVariableLookup, WdlWorkflowBlock}
import winstanley.references.{WdlCallableDeclarationReference, WdlVariableDeclarationReference}
import winstanley.structure.WdlImplicits._


/**
  * This class is used by the .bnf compiler to implement the 'methods=[...]' methods on PsiElements.
  *
  * Put all your other junk util methods somewhere else!
  */
object WdlPsiImplUtil extends
  WdlNamedElementImplUtil with
  WdlNamedTaskElementImplUtil with
  WdlVariableLookupImplUtil with
  WdlCallableLookupImplUtil

/**
  * Provides the getName, setName and getNameIdentifier methods for the WdlNamedElement subclasses (see (eg) declaration and scatter_declaration in the bnf)
  */
sealed trait WdlNamedElementImplUtil {
  def getName(namedElement: WdlNamedElementImpl): String = namedElement.declaredValueName.orNull
  // TODO: Implement for "refactor/rename" functionality
  def setName(namedElement: WdlNamedElementImpl, newName: String): PsiElement = ???
  def getNameIdentifier(namedElement: WdlNamedElementImpl): PsiElement = namedElement.getIdentifierNode.map(_.getPsi).orNull
}

sealed trait WdlNamedTaskElementImplUtil {
  def getName(namedTaskElement: WdlNamedTaskElementImpl): String = namedTaskElement.declaredValueName.orNull
  // TODO: Implement for "refactor/rename" functionality
  def setName(namedTaskElement: WdlNamedTaskElementImpl, newName: String): PsiElement = ???
  def getNameIdentifier(namedTaskElement: WdlNamedTaskElementImpl): PsiElement = namedTaskElement.getTaskIdentifierNode.map(_.getPsi).orNull
}

sealed trait WdlVariableLookupImplUtil {
  /**
    * This is the method that enables the 'go to declaration' functionality for variable usages.
    */
  def getReferences(wdlVariableLookup: WdlVariableLookup): Array[PsiReference] = Array(WdlVariableDeclarationReference(wdlVariableLookup))
}

sealed trait WdlCallableLookupImplUtil {
  /**
    * This is the method that enables the 'go to declaration' functionality for task usages.
    */
  def getReferences(wdlCallableLookup: WdlCallableLookup): Array[PsiReference] = Array(WdlCallableDeclarationReference(wdlCallableLookup))
}
