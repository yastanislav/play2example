lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := "play2example",
    version := "2.7.x",
    scalaVersion := "2.12.0",
    libraryDependencies ++= Seq(
      evolutions,
      jdbc,
      guice,
      javaJpa,
      "com.h2database" % "h2" % "1.4.199",
      "org.hibernate" % "hibernate-core" % "5.4.2.Final",
      "io.dropwizard.metrics" % "metrics-core" % "3.2.6",
      "com.palominolabs.http" % "url-builder" % "1.1.0",
      "net.jodah" % "failsafe" % "1.0.5",
      "org.mybatis" % "mybatis" % "3.5.2",
      "org.mybatis" % "mybatis-guice" % "3.3",
      "io.swagger.core.v3" % "swagger-core" % "2.0.9",
      "io.swagger.core.v3" % "swagger-annotations" % "2.0.9",
      "io.swagger" %% "swagger-play2" % "1.7.1"
    ),
    PlayKeys.externalizeResources := false,
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v")),
    javacOptions ++= Seq(
      "-Xlint:unchecked",
      "-Xlint:deprecation"
    )
  )

val gatlingVersion = "3.1.3"
lazy val gatling = (project in file("gatling"))
  .enablePlugins(GatlingPlugin)
  .settings(
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
      "io.gatling" % "gatling-test-framework" % gatlingVersion % Test
    )
  )
