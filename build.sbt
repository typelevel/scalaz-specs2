import sbtrelease._
import sbtrelease.ReleasePlugin._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.Utilities._

import com.typesafe.sbt.pgp.PgpKeys._

// Build configuration

organization := "org.typelevel"

name := "scalaz-specs2"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalaVersion := "2.10.3"

crossScalaVersions := Seq("2.10.3", "2.11.0")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.scalacheck" %% "scalacheck" % "1.11.3",
  "org.specs2" %% "specs2" % "2.3.11"
)

// Release plugin

lazy val publishSignedArtifacts = ReleaseStep(
  action = st => {
    val extracted = st.extract
    val ref = extracted.get(thisProjectRef)
    extracted.runAggregated(publishSigned in Global in ref, st)
  },
  check = st => {
    // getPublishTo fails if no publish repository is set up.
    val ex = st.extract
    val ref = ex.get(thisProjectRef)
    Classpaths.getPublishTo(ex.get(publishTo in Global in ref))
    st
  },
  enableCrossBuild = true
)

releaseSettings

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishSignedArtifacts,
  setNextVersion,
  commitNextVersion
)

// Publishing

publishTo <<= (version).apply { v =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("Snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("Releases" at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(
  Option(System.getProperty("build.publish.credentials")) map (new File(_)) getOrElse (Path.userHome / ".ivy2" / ".credentials")
)

pomIncludeRepository := Function.const(false)

pomExtra := (
  <scm>
    <url>https://github.com/typelevel/scalaz-specs2</url>
    <connection>scm:git:git://github.com/typelevel/scalaz-specs2.git</connection>
  </scm>
  <developers>
    <developer>
      <id>larsrh</id>
      <name>Lars Hupel</name>
      <url>https://github.com/larsrh</url>
    </developer>
    <developer>
      <id>arosien</id>
      <name>Adam Rosien</name>
      <url>https://github.com/arosien</url>
    </developer>
  </developers>
)
