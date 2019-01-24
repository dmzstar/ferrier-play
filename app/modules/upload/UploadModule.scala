package modules.upload

import com.google.inject.AbstractModule
import play.api.{Configuration, Environment}
import java.io.IOException
import java.nio.file._

import javax.inject.Singleton

import scala.util.control.Exception._
import play.api.Logger

import play.api.{Configuration, Environment, Mode}

class UploadModule (environment: Environment, configuration: Configuration) extends AbstractModule {

  def base = configuration.get[String]("upload.base")
  def baseUrl = configuration.get[String]("upload.base")

  def mkBase = {

      val target = Paths.get(base)
      catching(classOf[FileAlreadyExistsException],classOf[IOException]) withTry{
          Files.createDirectories(target)
      }

  }

  override def configure(): Unit = {
    val mode = environment.mode
    if(mode == Mode.Dev){

    }
    Logger.info(environment.rootPath + "")
  }

}


trait UploadComponent{
  //var uploadConfig:UploadConfig
  def createDir
  def store
}

class UploadComponentImpl  extends  UploadComponent{
  override def createDir: Unit = {

  }

  override def store: Unit = {

  }
}

import javax.inject.Inject

@Singleton
class UploadContext @Inject() (val configuration: Configuration,val env:Environment){

}
