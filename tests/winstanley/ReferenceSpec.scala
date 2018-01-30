package winstanley

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import winstanley.psi.{WdlDeclaration, WdlTaskDeclaration}
import winstanley.psi.impl.{WdlDeclarationImpl, WdlTaskDeclarationImpl}

import scala.reflect.ClassTag

class ReferenceSpec extends LightCodeInsightFixtureTestCase {

  def testPositiveTaskReference(): Unit = referenceTest[WdlTaskDeclarationImpl]("positive_task_reference.wdl", "hello_task")

  def testPositiveVariableReference(): Unit = referenceTest[WdlDeclarationImpl]("positive_variable_reference.wdl", "i")

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
