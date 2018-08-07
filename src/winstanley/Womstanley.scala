package winstanley

import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.ui.ConsoleViewContentType.{ERROR_OUTPUT, NORMAL_OUTPUT}
import com.intellij.notification.NotificationType.INFORMATION
import com.intellij.notification.{Notification, Notifications}
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.progress.{PerformInBackgroundOption, ProgressIndicator, ProgressManager, Task}
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindowManager
import cromwell.core.path.{DefaultPathBuilder, Path}
import womtool.WomtoolMain.{SuccessfulTermination, Termination, UnsuccessfulTermination}
import womtool.validate.Validate

import scala.util.{Failure, Success, Try}

object Womstanley {

  class Validate extends AnAction {
    override def actionPerformed(e: AnActionEvent): Unit = {

      // If focus is on something other than a file (like the console) we'll get null
      val currentFile: VirtualFile = e.getData(VIRTUAL_FILE)
      if (currentFile != null) {
        val path: Path = DefaultPathBuilder.build(currentFile.getPath).toOption.get

        // Maybe: find same-named inputs JSON and pass it in as second param?


        val toolWindow = ToolWindowManager.getInstance(e.getProject).getToolWindow("Womtool")
        toolWindow.getContentManager.removeAllContents(true)

        assert(toolWindow != null, "Attempted to write console output to non-existent tool window")

        val console = TextConsoleBuilderFactory.getInstance().createBuilder(e.getProject).getConsole
        // A "content" in IntelliJ is a tool window tab
        val content = toolWindow.getContentManager.getFactory.createContent(console.getComponent, "WDL Validation", true)
        toolWindow.getContentManager.addContent(content)

        toolWindow.getContentManager.setSelectedContent(content, true, true)

        toolWindow.activate(null)

        console.print(s"Validating ${currentFile.getPresentableName}...${System.lineSeparator()}", NORMAL_OUTPUT)

//        ProgressManager.getInstance().run(new Task.Backgroundable(e.getProject, "Womtool Validate", false, PerformInBackgroundOption.ALWAYS_BACKGROUND) {
//          override def run(progressIndicator: ProgressIndicator): Unit = {
//            progressIndicator.setIndeterminate(true)
//          }
//        })

        val result: Try[Termination] = Try {
          Validate.validate(path, None)
        }

        result match {
          case Success(success) =>
            success match {
              case success: SuccessfulTermination =>
                console.print(s"Success." + success.stdout.get, NORMAL_OUTPUT)
              case failure: UnsuccessfulTermination =>
                console.print(failure.stderr.get, ERROR_OUTPUT)
            }
          case Failure(failure) =>
            console.print(s"Failed with reason:${System.lineSeparator()}", ERROR_OUTPUT)
            console.print(failure.getMessage, ERROR_OUTPUT)
        }


      }
    }
  }

  // Other WOMtool commands...
//  class Inputs extends AnAction {
//
//  }
}
