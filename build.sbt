import org.typelevel.sbt._

// Build configuration

organization := "org.typelevel"

name := "scalaz-specs2"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.0",
  "org.scalacheck" %% "scalacheck" % "1.12.0",
  "org.specs2" %% "specs2-core" % "3.0-M0",
  "org.specs2" %% "specs2-scalacheck" % "3.0-M0"
)

resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
                  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")

// sbt-typelevel plugin

typelevelDefaultSettings

TypelevelKeys.githubProject := ("typelevel", "scalaz-specs2")

TypelevelKeys.githubDevs ++= Seq(
  Developer("Lars Hupel", "larsrh"),
  Developer("Adam Rosien", "arosien")
)

typelevelBuildInfoSettings

buildInfoPackage := "org.specs2.scalaz"
