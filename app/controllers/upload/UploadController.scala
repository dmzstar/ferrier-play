package controllers.upload

import java.nio.file._

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.{Configuration, Environment, Mode}
import play.api.Logger

class UploadController @Inject()(cc: ControllerComponents, configuration:Configuration, env:Environment) extends AbstractController(cc){

  def index = Action{
    Ok(views.html.example.upload())
  }

  def formUpload = Action(parse.multipartFormData) { request =>

    request.body.file("picture").map { f =>
      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      val filename = System.nanoTime() + suffix(Paths.get(f.filename).getFileName.toFile.getName)
      val storePath = if(env.mode == Mode.Dev) env.rootPath + s"/public/uploads/$filename" else s"/tmp/picture/$filename"
      Logger.info(storePath)
      f.ref.moveTo(Paths.get(storePath), replace = false)

    }.getOrElse {
      Ok("Error")
    }
    Ok("hello")

  }

  def ajaxUpload = Action(parse.temporaryFile) { request =>
    request.body.moveTo(Paths.get("/tmp/picture/uploaded"), replace = true)
    Ok("File uploaded")
  }

  def suffix(name:String) = name.substring(name.lastIndexOf("."))

}

object MainApp extends App{
  var name = "abc.jpg"
  //println(fileSubfix(name))
}
