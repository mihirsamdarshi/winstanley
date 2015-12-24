package winstanley

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import javax.swing._
import java.util.Map

object WdlColorSettingsPage {
  val DESCRIPTORS: Array[AttributesDescriptor] = Seq(
    new AttributesDescriptor("Key", WdlSyntaxHighlighter.KEY),
    new AttributesDescriptor("Separator", WdlSyntaxHighlighter.SEPARATOR),
    new AttributesDescriptor("Value", WdlSyntaxHighlighter.VALUE)).toArray
}

class WdlColorSettingsPage extends ColorSettingsPage {
  import WdlColorSettingsPage._

  @Nullable
  override def getIcon: Icon = WdlIcons.FILE

  @NotNull
  override def getHighlighter: SyntaxHighlighter = new WdlSyntaxHighlighter()

  @NotNull
  override def getDemoText: String = "# You are reading the \".properties\" entry.\n" +
  "! The exclamation mark can also mark text as comments.\n" +
  "website = http://en.wikipedia.org/\n" +
  "language = English\n" +
  "# The backslash below tells the application to continue reading\n" +
  "# the value onto the next line.\n" +
  "message = Welcome to \\\n" +
  "          Wikipedia!\n" +
  "# Add spaces to the key\n" +
  "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
  "# Unicode\n" +
  "tab : \\u0009"

  @Nullable
  override def getAdditionalHighlightingTagToDescriptorMap: Map[String, TextAttributesKey] = null

  @NotNull
  override def getAttributeDescriptors: Array[AttributesDescriptor] = DESCRIPTORS

  @NotNull
  override def getColorDescriptors: Array[ColorDescriptor] = ColorDescriptor.EMPTY_ARRAY

  @NotNull
  override def getDisplayName: String = "WDL"
}