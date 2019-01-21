package controllers

import javax.inject._
import models.cms.Article
import play.api._
import play.api.mvc._
import models.{Home, User}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def hello = Action{
    val s = Home.find.all()
    val a = new Article
    a.save()
    a.publish
    println("hello" + s)
    Ok(views.html.hello())
  }

}
