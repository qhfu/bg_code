name := """test-scala-play-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(  
  "org.sorm-framework" % "sorm" % "0.3.15",
  "com.h2database" % "h2" % "1.4.177",
  "org.pegdown" % "pegdown" % "1.4.2",
  "mysql" % "mysql-connector-java" % "5.1.21"
)     

libraryDependencies ++= Seq(
  jdbc,
  anorm
)