package modules.security

import com.google.inject.{AbstractModule, Provides}
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer
import org.pac4j.core.config.Config
import org.pac4j.core.client.Clients
import org.pac4j.core.client.direct.AnonymousClient
import org.pac4j.core.matching.PathMatcher
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.play.scala.{DefaultSecurityComponents, SecurityComponents}
import org.pac4j.play.{CallbackController, LogoutController}
import org.pac4j.play.store.{PlayCacheSessionStore, PlaySessionStore}
import play.api.Configuration
import play.api.Environment
import play.api.inject.Binding
import play.api.inject.Module
import org.pac4j.play.http.DefaultHttpActionAdapter

class SecurityModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  val baseUrl = configuration.get[String]("baseUrl")

  override def configure(): Unit = {

    bind(classOf[PlaySessionStore]).to(classOf[PlayCacheSessionStore])

    // callback
    val callbackController = new CallbackController()
    callbackController.setDefaultUrl(baseUrl + "/?defaulturlafterlogout")
    callbackController.setMultiProfile(true)
    bind(classOf[CallbackController]).toInstance(callbackController)

    // logout
    val logoutController = new LogoutController()
    logoutController.setDefaultUrl("/logout")
    bind(classOf[LogoutController]).toInstance(logoutController)

    // security components used in controllers
    bind(classOf[SecurityComponents]).to(classOf[DefaultSecurityComponents])
  }

  @Provides
  def provideFormClient: FormClient = new FormClient("/login", new SimpleTestUsernamePasswordAuthenticator())

  @Provides
  def provideConfig(formClient: FormClient): Config = {
    val clients = new Clients(baseUrl+ "/callback", formClient,
      new AnonymousClient())
    val config = new Config(clients)
    config.addAuthorizer("admin", new RequireAnyRoleAuthorizer[Nothing]("ROLE_ADMIN"))
    config.addMatcher("excludedPath", new PathMatcher().excludeRegex("^/login$"))
    config.setHttpActionAdapter(new DefaultHttpActionAdapter())
    config
  }

}
