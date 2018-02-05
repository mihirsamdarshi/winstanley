package winstanley

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import winstanley.psi.impl.{WdlDeclarationImpl, WdlTaskDeclarationImpl}

class ReferenceSpec extends LightCodeInsightFixtureTestCase {

  def testPositiveTaskReference(): Unit = referenceTest[WdlTaskDeclarationImpl]("positive_task_reference.wdl", "hello_task")

  def testPositiveVariableLookupReference(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_lookup_reference.wdl", "i")

  def testPositiveCallBlockReference(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference.wdl", "me")

  def testPositiveAliasReference(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference.wdl", "my_alias")

  def testPositiveAliasReferenceWithDecoyTask(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_decoy_task.wdl", "bar")

  def testPositiveVariableReferenceInScatter(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference_in_scatter.wdl", "foo")

  def testPositiveVariableReferenceInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference_in_conditional.wdl", "foo")

  def testPositiveVariableReferenceInNestedConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference_in_nested_conditional.wdl", "foo")

  def testPositiveVariableReferenceConditionalInScatter(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference_conditional_in_scatter.wdl", "that")

  def testPositiveVariableReferenceScatterInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference_scatter_in_conditional.wdl", "that")

  def testPositiveVariableReferenceNestedScatterInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference_nested_scatter_in_conditional.wdl", "that")

  def testPositiveCallBlockReferenceInScatter(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference_in_scatter.wdl", "foo")

  def testPositiveCallBlockReferenceInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference_in_conditional.wdl", "foo")

  def testPositiveCallBlockReferenceInNestedConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference_in_nested_conditional.wdl", "foo")

  def testPositiveCallBlockReferenceConditionalInScatter(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference_conditional_in_scatter.wdl", "foo")

  def testPositiveCallBlockReferenceScatterInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference_scatter_in_conditional.wdl", "foo")

  def testPositiveCallBlockReferenceNestedScatterInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_call_block_reference_nested_scatter_in_conditional.wdl", "foo")

  def testPositiveAliasReferenceInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_in_conditional.wdl", "oof")

  def testPositiveAliasReferenceInScatter(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_in_scatter.wdl", "oof")

  def testPositiveAliasReferenceInNestedConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_in_nested_conditional.wdl", "oof")

  def testPositiveAliasReferenceConditionalInScatter(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_conditional_in_scatter.wdl", "oof")

  def testPositiveAliasReferenceScatterInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_scatter_in_conditional.wdl", "oof")

  def testPositiveAliasReferenceNestedScatterInConditional(): Unit = referenceTest[WdlDeclarationImpl]("positive_alias_reference_nested_scatter_in_conditional.wdl", "oof")

  /**
    * Various cases of
    * @param path The path to the test wdl
    * @param expectedReferenceText The name of the expected reference
    * @tparam ExpectedReferenceType The type of the expected reference
    */
  private def referenceTest[ExpectedReferenceType](path: String, expectedReferenceText: String): Unit = {
    myFixture.configureByFile(path)
    val resolvedReference = myFixture.getReferenceAtCaretPositionWithAssertion(path).resolve()
    assert(resolvedReference.getParent.isInstanceOf[ExpectedReferenceType])
    assert(resolvedReference.getText == expectedReferenceText)
  }

  override def setUp(): Unit = super.setUp()
  override def tearDown(): Unit = super.tearDown()
  override def getTestDataPath: String = "tests/testData/reference/"
}
