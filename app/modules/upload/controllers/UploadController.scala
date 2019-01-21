package modules.upload.controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import java.nio.file._

import play.api.Configuration

class UploadController @Inject()(cc: ControllerComponents,configuration:Configuration) extends AbstractController(cc){

  def formUpload = Action(parse.multipartFormData) { request =>
    /**
    request.body.files.foreach(f => {
      val filename = Paths.get(f.filename).getFileName
      f.ref.moveTo(Paths.get(s"/tmp/picture/$filename"), replace = true)
      Ok("File uploaded")
    })*/

    /**
    request.body.file("picture").map { picture =>
      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system

    }.getOrElse {
      Ok("Error")
    }*/
    Ok("hello")
  }

  def ajaxUpload = Action(parse.temporaryFile) { request =>
    request.body.moveTo(Paths.get("/tmp/picture/uploaded"), replace = true)
    Ok("File uploaded")
  }

}
