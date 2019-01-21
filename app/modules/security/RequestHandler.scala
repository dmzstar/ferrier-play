package modules.security

import javax.inject.Inject
import play.api.http.{DefaultHttpRequestHandler, HttpConfiguration, HttpErrorHandler, HttpFilters}
import play.api.mvc.RequestHeader
import play.api.routing.Router

class RequestHandler @Inject()(errorHandler: HttpErrorHandler,
                               configuration: HttpConfiguration, filters: HttpFilters,
                                          router:Router) extends DefaultHttpRequestHandler(
  router,errorHandler, configuration, filters
) {

  override def routeRequest(request: RequestHeader) = {



    request.path match {
      case "foo.example.com" => router.routes.lift(request)
      case _ => super.routeRequest(request)
    }

  }
}


class SecurityMapping{

}
