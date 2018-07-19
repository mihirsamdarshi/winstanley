package winstanley

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.vfs.VirtualFile
import cromwell.core.path.{DefaultPathBuilder, Path}
import womtool.validate.Validate
import womtool.WomtoolMain.{SuccessfulTermination, Termination, UnsuccessfulTermination}


class Womstanley extends AnAction {
  override def actionPerformed(e: AnActionEvent): Unit = {
    val currentFile: VirtualFile = e.getData(VIRTUAL_FILE).asInstanceOf[VirtualFile]
    val path: Path = DefaultPathBuilder.build(currentFile.getPath).toOption.get // TODO: proper error handling

    val result: Termination = Validate.validate(path, None)

    result match {
      case success: SuccessfulTermination => println(success.stdout)
      case failure: UnsuccessfulTermination => println(failure.stderr)
      case _ => ??? // TODO: proper error handling
    }
  }
}
