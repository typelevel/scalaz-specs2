name := "scalaz-specs2"

version := "0.1.5"

organization := "org.typelevel"

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("http://typelevel.org/"))

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.9.3", "2.10.2")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.3",
  "org.scalacheck" %% "scalacheck" % "1.10.0"
)

libraryDependencies <+= (scalaVersion) { sv =>
  val specsVersion =
    if (sv startsWith "2.9")
      "1.12.4.1"
    else
      "1.12.3"
  "org.specs2" %% "specs2" % specsVersion
}

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
