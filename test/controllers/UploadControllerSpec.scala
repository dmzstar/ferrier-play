package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import controllers.upload.UploadController
import modules.upload.UploadContext

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class UploadControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the upload page from a new instance of controller" in {
      val controller = new UploadController(stubControllerComponents(),inject[UploadContext])
      val home = controller.index().apply(FakeRequest(GET, "/test/upload"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to PlayExample")
    }

    /**
    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }
      */

    "render the upload page from the router" in {
      val request = FakeRequest(GET, "/test/upload")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to PlayExample")
    }


  }
}
