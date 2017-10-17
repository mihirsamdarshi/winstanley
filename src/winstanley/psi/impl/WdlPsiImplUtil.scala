package winstanley.psi.impl

import com.intellij.psi.{PsiElement, PsiReference}
import winstanley.psi.{WdlVariableLookup, WdlWorkflowBlock}
import winstanley.references.WdlDeclarationReference
import winstanley.structure.WdlImplicits._


/**
  * This class is used by the .bnf compiler to implement the 'methods=[...]' methods on PsiElements.
  *
  * Put all your other junk util methods somewhere else!
  */
object WdlPsiImplUtil extends
  WdlNamedElementImplUtil with
  WdlVariableLookupImplUtil

/**
  * Provides the getName, setName and getNameIdentifier methods for the WdlNamedElement subclasses (see (eg) declaration and scatter_declaration in the bnf)
  */
sealed trait WdlNamedElementImplUtil {
  def getName(namedElement: WdlNamedElementImpl): String = namedElement.declaredValueName.orNull
  // TODO: Implement for "refactor/rename" functionality
  def setName(namedElement: WdlNamedElementImpl, newName: String): PsiElement = ???
  def getNameIdentifier(namedElement: WdlNamedElementImpl): PsiElement = namedElement.getIdentifierNode.map(_.getPsi).orNull
}

sealed trait WdlVariableLookupImplUtil {
  /**
    * This is the method that enables the 'go to declaration' functionality for variable usages.
    */
  def getReferences(wdlVariableLookup: WdlVariableLookup): Array[PsiReference] = Array(WdlDeclarationReference(wdlVariableLookup))
}

