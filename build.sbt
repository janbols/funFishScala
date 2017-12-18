name := "funFishScala"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(ScalaJSPlugin)

name := "Scala.js version of funFish"

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
libraryDependencies += "org.scala-js" %%% "scalajs-java-logging" % "0.1.3"