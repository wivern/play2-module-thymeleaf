name := "scala_sample"

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq()

//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

lazy val lib = RootProject(file("../.."))

val `scala_sample` = (project in file(".")).enablePlugins(PlayScala).dependsOn(lib)

