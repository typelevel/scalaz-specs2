name := "scalaz-specs2"

version := "0.1"

organization := "org.typelevel"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.10.0")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.0-M7",
  "org.specs2" %% "specs2" % "1.12.3",
  "org.scalacheck" %% "scalacheck" % "1.10.0"
)

publishTo <<= (version) { v =>
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
  <url>http://typelevel.org/scalaz</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/larsrh/scalaz-specs2</url>
    <connection>scm:git:git://github.com/larsrh/scalaz-specs2.git</connection>
    <developerConnection>scm:git:git@github.com:larsrh/scalaz-specs2.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>larsrh</id>
      <name>Lars Hupel</name>
      <url>https://github.com/larsrh</url>
    </developer>
  </developers>
)

// vim: expandtab:ts=2:sw=2
