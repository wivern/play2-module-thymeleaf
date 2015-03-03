name := "play2-module-thymeleaf"

version := "1.0"

scalaVersion := "2.11.6"

lazy val root = (project in file(".")).enablePlugins()

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.3.8",
  "org.thymeleaf" % "thymeleaf" % "2.1.4.RELEASE"
)