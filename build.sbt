name := "scalaz-specs2"

version := "0.3-SNAPSHOT"

organization := "org.typelevel"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalaVersion := "2.10.3"

crossScalaVersions := Seq("2.10.3", "2.11.0-M8")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.5",
  "org.scalacheck" %% "scalacheck" % "1.11.3",
  "org.specs2" %% "specs2" % "2.3.7"
)

resolvers += Resolver.sonatypeRepo("releases")

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

// vim: expandtab:ts=2:sw=2
