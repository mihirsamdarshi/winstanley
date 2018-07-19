package winstanley

import com.intellij.notification.{Notification, Notifications}
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.vfs.VirtualFile
import cromwell.core.path.{DefaultPathBuilder, Path}
import womtool.validate.Validate
import womtool.WomtoolMain.{SuccessfulTermination, Termination, UnsuccessfulTermination}
import com.intellij.notification.NotificationType.{INFORMATION, ERROR}


object Womstanley {

  class Validate extends AnAction {
    override def actionPerformed(e: AnActionEvent): Unit = {

      // TODO: proper error handling
      // TODO: If focus is on something other than a file (like the console) this will fail
      val currentFile: VirtualFile = e.getData(VIRTUAL_FILE).asInstanceOf[VirtualFile]
      val path: Path = DefaultPathBuilder.build(currentFile.getPath).toOption.get

      // Maybe: find same-named inputs JSON and pass it in as second param?
      val result: Termination = Validate.validate(path, None)

      result match {
        case success: SuccessfulTermination =>
          // HTML works here!
          val notification = new Notification("WOMtool", "WDL Validation Succeeded", "<i>" + currentFile.getName + "</i>", INFORMATION)
          Notifications.Bus.notify(notification, e.getProject) // I'm not exactly sure what specifying the (optional) project gets us, but it's free so why not
          println(success.stdout)
        case failure: UnsuccessfulTermination =>
          val notification = new Notification("WOMtool", "WDL Validation Failed", failure.stderr.get, ERROR)
          Notifications.Bus.notify(notification, e.getProject)
          println(failure.stderr)
        case _ => ??? // TODO: proper error handling
      }
    }
  }

  // Other WOMtool commands...
//  class Inputs extends AnAction {
//
//  }
}

