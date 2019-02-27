organization := "org.ergoplatform"
name := "ergo"
version := "1.9.1"
scalaVersion := "2.12.8"

//resolvers +=  Resolver.bintrayRepo("hseeberger", "maven")
resolvers ++= Seq(
  //"Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "SonaType" at "https://oss.sonatype.org/content/groups/public",
  "Typesafe maven releases" at "http://repo.typesafe.com/typesafe/maven-releases/")
homepage := Some(url("http://ergoplatform.org/"))
licenses := Seq("CC0" -> url("https://creativecommons.org/publicdomain/zero/1.0/legalcode"))
pomExtra :=
  <dependency>
    <groupId>org.scorexfoundation</groupId>
    <artifactId>sigma-state</artifactId>
    <version>v2.0-25427b6b-SNAPSHOT</version>
    <scope>test</scope>
  </dependency>

val scorexVersion = "2.0.0-RC3"
val sigmastate = "org.scorexfoundation" %% "sigma-state" % "v2.0-25427b6b-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async" % "0.9.7",
  ("org.scorexfoundation" %% "avl-iodb" % "0.2.15").exclude("ch.qos.logback", "logback-classic"),
  "org.scorexfoundation" %% "iodb" % "0.3.2",
  ("org.scorexfoundation" %% "scorex-core" % scorexVersion).exclude("ch.qos.logback", "logback-classic"),

  "javax.xml.bind" % "jaxb-api" % "2.+",
  "com.iheart" %% "ficus" % "1.4.+",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.google.guava" % "guava" % "21.0",

  "com.storm-enroute" %% "scalameter" % "0.8.+" % "test",
  "org.scalactic" %% "scalactic" % "3.0.+" % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.+" % "test",
/*
  ("org.scorexfoundation" %% "sigma-state" % "master-5f01afb6-SNAPSHOT" % "test")
*/
  ("org.scorexfoundation" %% "sigma-state" % "v2.0-25427b6b-SNAPSHOT")
    .exclude("ch.qos.logback", "logback-classic")
    .exclude("org.scorexfoundation", "scrypto"),
  /*("org.scorexfoundation" %% "sigma-state" % "v2.0-25427b6b-SNAPSHOT" % Test).classifier("tests")
    .exclude("ch.qos.logback", "logback-classic")
    .exclude("org.scorexfoundation", "scrypto"),*/
  "com.typesafe.akka" %% "akka-testkit" % "2.5.+" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.+" % "test",
  "org.asynchttpclient" % "async-http-client" % "2.6.+" % "test",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-properties" % "2.9.2" % "test"/*,
  sigma % Test*/
)

fork := true

val opts = Seq(
  "-server",
  // JVM memory tuning for 2g ram
  "-Xms128m",
  "-Xmx2G",
  //64M for stack, reduce after optimizations
  "-Xss64m",
  "-XX:+ExitOnOutOfMemoryError",
  // Java 9 support
  "-XX:+IgnoreUnrecognizedVMOptions",
  "--add-modules=java.xml.bind",

  // from https://groups.google.com/d/msg/akka-user/9s4Yl7aEz3E/zfxmdc0cGQAJ
  "-XX:+UseG1GC",
  "-XX:+UseNUMA",
  "-XX:+AlwaysPreTouch",

  // probably can't use these with jstack and others tools
  "-XX:+PerfDisableSharedMem",
  "-XX:+ParallelRefProcEnabled",
  "-XX:+UseStringDeduplication")

// -J prefix is required by the bash script
javaOptions in run ++= opts
scalacOptions ++= Seq("-Xfatal-warnings", "-feature", "-deprecation")
/*

lazy val ergo = (project in file("."))
  .settings(commonSettings,
   libraryDependencies ++= Seq(sigma, (sigma % Test).classifier("tests")))

*/