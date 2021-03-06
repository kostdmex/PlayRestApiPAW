name := "PlayRestApiPAW"
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += javaJdbc
//libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

enablePlugins(DockerSpotifyClientPlugin)

enablePlugins(DockerPlugin)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.18",
)
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.2.Final"
)
libraryDependencies += "org.glassfish.jaxb" % "jaxb-core" % "2.3.0.1"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2"
libraryDependencies += "com.auth0" % "java-jwt" % "3.3.0"
