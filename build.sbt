name := """ferrier-play"""
organization := "com.weifan"

version := "1.0-SNAPSHOT"

lazy val domain = (project in file("modules/domain")).enablePlugins(PlayScala, PlayEbean)
lazy val admin = (project in file("modules/admin")).enablePlugins(PlayScala, PlayEbean).dependsOn(domain).aggregate(domain)

lazy val root = (project in file(".")).enablePlugins(PlayScala, PlayEbean)
  .dependsOn(domain).aggregate(domain)
  .dependsOn(admin).aggregate(admin)


scalaVersion := "2.12.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

/**
initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}*/


libraryDependencies ++= Seq(
  "org.pac4j" %% "play-pac4j" % "6.1.0-SNAPSHOT",
  // https://mvnrepository.com/artifact/org.pac4j/pac4j-http
  "org.pac4j" % "pac4j-http" % "3.4.0",
  // https://mvnrepository.com/artifact/org.pac4j/pac4j-config
  "org.pac4j" % "pac4j-config" % "3.4.0",
  "be.objectify" %% "deadbolt-scala" % "2.6.0",
  ehcache
)
libraryDependencies += jcache
libraryDependencies += "org.jsr107.ri" % "cache-annotations-ri-guice" % "1.0.0"
resolvers += Resolver.sonatypeRepo("snapshots")

//libraryDependencies += "com.bowlingx" %% "play-webpack" % "0.1.19"
//webpackManifest := file("conf/webpack-assets.json").some.filter(_.exists).toSeq

libraryDependencies += "org.webjars" %% "webjars-play" % "2.6.3"
//libraryDependencies += "org.webjars" % "requirejs" % "2.2.0"
libraryDependencies += "org.webjars" % "bootstrap" % "4.2.1"

// Resolver is needed only for SNAPSHOT versions
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
libraryDependencies ++= Seq(
  "com.adrianhurt" %% "play-bootstrap" % "1.4-P26-B4-SNAPSHOT"
)

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test


//TwirlKeys.constructorAnnotations += "@javax.inject.Inject()"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.weifan.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.weifan.binders._"
