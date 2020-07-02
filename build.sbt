scalaVersion := "2.12.8"

sbtVersion := "0.13.17"

organization := "ai.economicdatasciences"

javaOptions := Seq("-Xmx4096M", "-Xms512m", "-XX:MaxPermSize=512m")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-math3" % "3.6.1",
  "org.jfree" % "jfreechart" % "1.5.0",
  "com.typesafe.akka" %% "akka-actor" % "2.6.1",
  "org.apache.spark" %% "spark-core" % "2.4.4",
  "org.apache.spark" %% "spark-mllib" % "2.4.4",
  "org.apache.spark" %% "spark-streaming" % "2.4.4",
  "org.scalatest" %% "scalatest" % "3.1.0"
)

// Resolver for Apache Spark framework
resolvers += "Akka Repository" at "https://repo.akka.io/releases/"
