package controllers

import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{AbstractController, ControllerComponents}

class SignupController @Inject()(cc: ControllerComponents,uploadC) extends AbstractController(cc)
  with play.api.i18n.I18nSupport{

  def index = Action{ implicit request =>
    val userForm = Form(
      mapping(
        "name" -> text,
        "age" -> number
      )(UserData.apply)(UserData.unapply)
    )
    Ok(views.html.signup(userForm))
  }

  def signup = Action{
    Ok("")
  }

}

case class UserData(name: String, age: Int)
