package controllers

import controllers.admin.cms.EditorController
import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class Router @Inject()(controller: EditorController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/") => controller.index
  }
}
