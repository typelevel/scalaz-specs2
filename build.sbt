name := "scalaz-specs2"

version := "0.1-SNAPSHOT"

organization := "org.typelevel"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.10.0-RC5")

crossVersion := CrossVersion.full

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" % "scalaz-core" % "7.0.0-M6" cross CrossVersion.full,
  "org.specs2" % "specs2" % "1.12.3" cross CrossVersion.full,
  "org.scalacheck" % "scalacheck" % "1.10.0" cross CrossVersion.full
)
