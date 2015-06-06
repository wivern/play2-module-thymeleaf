name := "play2-module-thymeleaf"

version := "1.0"

scalaVersion := "2.11.6"

lazy val play2_module_thymeleaf = (project in file(".")).enablePlugins()

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.4.0" withSources(),
  "org.thymeleaf" % "thymeleaf" % "2.1.4.RELEASE" withSources()
)

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe repository mwn" at "http://repo.typesafe.com/typesafe/maven-releases/"
