package winstanley.parsing

import com.intellij.testFramework.ParsingTestCase
import winstanley.WdlParserDefinition

class ParsingSpec extends ParsingTestCase("", "wdl", new WdlParserDefinition()) {

  // To add a new parsing test:
  // - Add a new WDL file into testData (eg Hello.wdl)
  // - Use PSI viewer to get a PSI breakdown of the file and copy into testData (eg Hello.txt)
  //  - NB: you might need to do some PSI whitespace tidying up around the edges
  // - Add a new test below (eg `def testHello(): Unit = doTest(true)`)

  def testHello(): Unit = doTest(true)

  override def getTestDataPath: String = "tests/testData/"
  override def skipSpaces(): Boolean = false
  override def includeRanges(): Boolean = true
}
