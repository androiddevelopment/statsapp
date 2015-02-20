import android.Keys._

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

android.Plugin.androidBuild

name := "gaastats"

scalaVersion := "2.10.0"

proguardCache in Android ++= Seq(
  ProguardCache("org.scaloid") % "org.scaloid"
)

proguardOptions in Android ++= Seq("-dontobfuscate", "-dontoptimize", "-keepattributes Signature", "-printseeds target/seeds.txt", "-printusage target/usage.txt"
  , "-dontwarn scala.collection.**" // required from Scala 2.11.4
)

libraryDependencies += "org.scaloid" % "scaloid_2.11" % "3.6.1-10" withSources() withJavadoc()

libraryDependencies += "com.j256.ormlite" % "ormlite-android" % "4.43"

libraryDependencies += "com.j256.ormlite" % "ormlite-core" % "4.43"

libraryDependencies += "joda-time" % "joda-time" % "2.1"

libraryDependencies += "commons-lang" % "commons-lang" % "2.3"

libraryDependencies += "org.joda" % "joda-convert" % "1.3.1"

apkbuildExcludes in Android += "META-INF/LICENSE.txt"

apkbuildExcludes in Android += "META-INF/NOTICE.txt"

scalacOptions += "-language:reflectiveCalls"

scalacOptions in Compile += "-feature"

run <<= run in Android

install <<= install in Android