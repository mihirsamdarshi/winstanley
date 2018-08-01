package winstanley

import com.intellij.execution.Executor
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.notification.{Notification, Notifications}
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.vfs.VirtualFile
import cromwell.core.path.{DefaultPathBuilder, Path}
import womtool.validate.Validate
import womtool.WomtoolMain.{SuccessfulTermination, Termination, UnsuccessfulTermination}
import com.intellij.notification.NotificationType.{ERROR, INFORMATION}
import com.intellij.execution.ui.ConsoleViewContentType.ERROR_OUTPUT
import com.intellij.openapi.wm.ToolWindowId
import javax.swing.Icon


object Womstanley {

  class Validate extends AnAction {
    override def actionPerformed(e: AnActionEvent): Unit = {

      // If focus is on something other than a file (like the console) we'll get null
      val currentFile: VirtualFile = e.getData(VIRTUAL_FILE)
      if (currentFile != null) {
        val path: Path = DefaultPathBuilder.build(currentFile.getPath).toOption.get

        // Maybe: find same-named inputs JSON and pass it in as second param?
        val result: Termination = Validate.validate(path, None)

        result match {
          case success: SuccessfulTermination =>
            // HTML works here!
            val notification = new Notification("WomTool", "WDL Validation Succeeded", "<i>" + currentFile.getName + "</i>", INFORMATION)
            Notifications.Bus.notify(notification, e.getProject) // I'm not exactly sure what specifying the (optional) project gets us, but it's free so why not
//            println(success.stdout)
          case failure: UnsuccessfulTermination =>

            import com.intellij.openapi.wm.ToolWindow
            import com.intellij.openapi.wm.ToolWindowManager

            val toolWindow = ToolWindowManager.getInstance(e.getProject).getToolWindow("Event Log")

            assert(toolWindow != null, "Attempted to write console output to non-existent tool window")

            val console = TextConsoleBuilderFactory.getInstance().createBuilder(e.getProject).getConsole
            val content = toolWindow.getContentManager.getFactory.createContent(console.getComponent, "WDL Messages", true)

            toolWindow.getContentManager.addContent(content)
            toolWindow.getContentManager.setSelectedContent(content, true, true)

            toolWindow.activate(null)

            console.print(failure.stderr.get, ERROR_OUTPUT)

          case _ => ??? // TODO: proper error handling
        }
      }
    }
  }

  // Other WOMtool commands...
//  class Inputs extends AnAction {
//
//  }
}

