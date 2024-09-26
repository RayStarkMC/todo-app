import sbt.Keys.*
import sbt.*

name := """api"""

organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
	.enablePlugins(PlayScala)

scalaVersion := "2.13.13"

libraryDependencies += guice

resolvers ++= Seq(
	"Nextbeat Snapshots" at "https://s3-ap-northeast-1.amazonaws.com/maven.nextbeat.net/snapshots",
	"Nextbeat Releases"  at "https://s3-ap-northeast-1.amazonaws.com/maven.nextbeat.net/releases",
)

libraryDependencies ++= Seq(
	// まずはこの1つから
	"net.ixias"      %% "ixias"               % "2.3.0",
	"net.ixias"      %% "ixias-slick"         % "2.3.0", // Slickを使用したDBアクセス
	"com.mysql" % "mysql-connector-j" % "8.3.0",
	"ch.qos.logback" % "logback-classic"      % "1.1.+",
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
