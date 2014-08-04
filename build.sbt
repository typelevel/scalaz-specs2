import org.typelevel.sbt._

// Build configuration

organization := "org.typelevel"

name := "scalaz-specs2"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.0",
  "org.scalacheck" %% "scalacheck" % "1.11.4",
  "org.specs2" %% "specs2" % "2.4"
)

resolvers += Resolver.sonatypeRepo("releases")

// sbt-typelevel plugin

typelevelDefaultSettings

TypelevelKeys.githubProject := ("typelevel", "scalaz-specs2")

TypelevelKeys.githubDevs ++= Seq(
  Developer("Lars Hupel", "larsrh"),
  Developer("Adam Rosien", "arosien")
)

typelevelBuildInfoSettings

buildInfoPackage := "org.specs2.scalaz"
