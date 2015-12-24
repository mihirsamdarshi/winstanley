package winstanley

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.annotations.NotNull

class WdlSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
  @NotNull
  override def getSyntaxHighlighter( project: Project, virtualFile: VirtualFile): SyntaxHighlighter = new WdlSyntaxHighlighter
}