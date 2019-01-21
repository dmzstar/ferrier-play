import org.pac4j.core.context.HttpConstants
import org.pac4j.play.PlayWebContext
import org.pac4j.play.http.DefaultHttpActionAdapter
import play.mvc.Results
import play.mvc.Result

class DemoHttpActionAdapter extends DefaultHttpActionAdapter {

  override def adapt(code: Int, context: PlayWebContext): Result = {

    if (code == HttpConstants.UNAUTHORIZED) {
      Results.unauthorized()
    } else if (code == HttpConstants.FORBIDDEN) {
      Results.forbidden("403")
    } else {
      super.adapt(code, context)
    }

  }
}
