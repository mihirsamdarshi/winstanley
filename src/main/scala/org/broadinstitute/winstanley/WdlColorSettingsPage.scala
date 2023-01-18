package org.broadinstitute.winstanley

import java.util

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import javax.swing._

import scala.language.postfixOps

object WdlColorSettingsPage {
  val DESCRIPTORS: Array[AttributesDescriptor] = WdlSyntaxHighlighter.mappings map {
    case (x, y) => new AttributesDescriptor(x.toString, y)
  } toArray
}

class WdlColorSettingsPage extends ColorSettingsPage {
  import WdlColorSettingsPage._

  @Nullable
  override def getIcon: Icon = WdlIcons.FILE

  @NotNull
  override def getHighlighter: SyntaxHighlighter = new WdlSyntaxHighlighter()

  @NotNull
  override def getDemoText: String =
    """
      |workflow myWorkflow {
      |  call myTask as taskname
      |}
    """.stripMargin

  @Nullable
  override def getAdditionalHighlightingTagToDescriptorMap: util.Map[String, TextAttributesKey] = null

  @NotNull
  override def getAttributeDescriptors: Array[AttributesDescriptor] = DESCRIPTORS

  @NotNull
  override def getColorDescriptors: Array[ColorDescriptor] = ColorDescriptor.EMPTY_ARRAY

  @NotNull
  override def getDisplayName: String = "WDL"
}