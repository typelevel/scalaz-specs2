import org.typelevel.sbt._

organization := "org.typelevel"

name := "scalaz-specs2"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= {
  // specs2 3.7 is the last version to use scalacheck 1.12.5
  val specs2Version = "3.9.5"
  Seq(
    "org.scalaz" %% "scalaz-core" % "7.2.15",
    "org.scalacheck" %% "scalacheck" % "1.13.5",
    "org.specs2" %% "specs2-core" % specs2Version,
    "org.specs2" %% "specs2-scalacheck" % specs2Version
  )
}

// sbt-typelevel plugin

typelevelDefaultSettings

TypelevelKeys.githubProject := ("typelevel", "scalaz-specs2")

TypelevelKeys.githubDevs ++= Seq(
  org.typelevel.sbt.Developer("Lars Hupel", "larsrh"),
  org.typelevel.sbt.Developer("Adam Rosien", "arosien")
)

typelevelBuildInfoSettings

buildInfoPackage := "org.specs2.scalaz"
