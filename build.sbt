name := "osrs-planner"

ThisBuild / scalaVersion := "2.13.6"

lazy val root =
  (project in file("."))
    .aggregate(wikiScraper, ui)

val circeVersion = "0.14.1"

lazy val wikiScraper =
  (project in file("scraper"))
    .settings(
      libraryDependencies ++= List(
        "net.ruippeixotog" %% "scala-scraper" % "2.2.1",
        "ch.qos.logback" % "logback-classic" % "1.2.3",
        "org.log4s" %% "log4s" % "1.8.2",
        "com.typesafe.akka" %% "akka-actor-typed" % "2.6.8",
        "com.typesafe.akka" %% "akka-stream" % "2.6.8",
        "com.typesafe.akka" %% "akka-http" % "10.2.4",
        "com.sksamuel.scrimage" % "scrimage-core" % "4.0.20",
        "com.sksamuel.scrimage" %% "scrimage-scala" % "4.0.20",
        "io.circe" %% "circe-core" % circeVersion
      )
    )

lazy val ui =
  (project in file("ui"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(
      libraryDependencies ++= List(
        "org.scala-js" %%% "scalajs-dom" % "1.1.0",
        "com.github.japgolly.scalajs-react" %%% "core" % "1.7.7",
        "io.circe" %%% "circe-core" % circeVersion,
        "io.circe" %%% "circe-generic" % circeVersion,
        "io.circe" %%% "circe-parser" % circeVersion,
        "io.circe" %%% "circe-scalajs" % circeVersion
      ),
      Compile / npmDependencies ++= List(
        "jsdom" -> "16.6.0",
        "react" -> "17.0.2",
        "react-dom" -> "17.0.2",
        "fuse.js" -> "6.5.3"
      ),
      scalaJSUseMainModuleInitializer := true,
      jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
      Test / requireJsDomEnv := true,
      webpack / version := "4.44.2",
      startWebpackDevServer / version := "3.11.2",
      (Compile / fastOptJS / scalaJSLinkerConfig) ~= { _.withSourceMap(false) },
      (Compile / fullOptJS / scalaJSLinkerConfig) ~= { _.withSourceMap(false) }
    )
