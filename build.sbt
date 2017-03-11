name := "scala-advanced-practice"

organization := "com.taintech"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
)

initialCommands := "import com.taintech._"
