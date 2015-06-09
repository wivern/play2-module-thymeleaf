name := "scala_sample"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq()

libraryDependencies += specs2 % Test

//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

lazy val lib = RootProject(file("../.."))

val `scala_sample` = (project in file(".")).enablePlugins(PlayScala).dependsOn(lib)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe repository mwn" at "http://repo.typesafe.com/typesafe/maven-releases/"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"