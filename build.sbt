import org.typelevel.sbt._

organization := "org.typelevel"

name := "scalaz-specs2"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.6", "2.11.7")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.0",
  "org.scalacheck" %% "scalacheck" % "1.12.5",
  "org.specs2" %% "specs2-core" % "3.6.6-scalaz-7.2.0",
  "org.specs2" %% "specs2-scalacheck" % "3.6.6-scalaz-7.2.0"
)

// sbt-typelevel plugin

typelevelDefaultSettings

TypelevelKeys.githubProject := ("typelevel", "scalaz-specs2")

TypelevelKeys.githubDevs ++= Seq(
  Developer("Lars Hupel", "larsrh"),
  Developer("Adam Rosien", "arosien")
)

typelevelBuildInfoSettings

buildInfoPackage := "org.specs2.scalaz"
