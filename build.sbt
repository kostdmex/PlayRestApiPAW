name := "PlayRestApiPAW"
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += javaJdbc

enablePlugins(DockerSpotifyClientPlugin)

enablePlugins(DockerPlugin)
