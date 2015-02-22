import android.Keys._

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

android.Plugin.androidBuild

name := "gaastats"

scalaVersion := "2.10.0"

//proguardCache in Android ++= Seq(
//  ProguardCache("org.scaloid") % "org.scaloid"
//)

proguardOptions in Android ++= Seq("-dontobfuscate", "-dontoptimize", "-keepattributes Signature", "-printseeds target/seeds.txt", "-printusage target/usage.txt"
  , "-dontwarn scala.collection.**"
)

proguardConfig in Android += "-dontobfuscate\n-dontoptimize\n-dontpreverify\n\n-dontwarn scala.**\n-dontwarn org.junit.runners.model.InitializationError\n-dontwarn android.support.v4.**\n-dontwarn com.xtremelabs.robolectric.**\n-dontwarn com.google.android.maps.MapActivity\n-dontwarn roboguice.activity.RoboMapActivity\n\n-dontnote javax.xml.**\n-dontnote org.w3c.dom.**\n-dontnote org.xml.sax.**\n-dontnote scala.Enumeration\n\n-keep public class com.gaastats.** { public protected *; }\n-keep public class scala.Option\n-keep public class scala.Function0\n-keep public class scala.Function1\n-keep public class scala.Function2\n-keep public class scala.Product\n-keep public class scala.Tuple2\n\n-keep public class scala.collection.Seq\n-keep public class scala.collection.immutable.List\n-keep public class scala.collection.immutable.Map\n-keep public class scala.collection.immutable.Seq\n-keep public class scala.collection.immutable.Set\n-keep public class scala.collection.immutable.Vector\n\n-keep public class * extends android.app.Activity\n-keep public class * extends android.app.Application\n-keep public class * extends android.app.Service\n-keep public class * extends android.app.backup.BackupAgentHelper\n-keep public class * extends android.appwidget.AppWidgetProvider\n-keep public class * extends android.content.BroadcastReceiver\n-keep public class * extends android.content.ContentProvider\n-keep public class * extends android.preference.Preference\n-keep public class * extends android.view.View\n-keepclassmembers class * {\n    @com.google.inject.Inject <init>(...);\n}\n\n-keepclassmembers class * { \n    void *(**On*Event); \n}\n-keep public class * extends android.view.View {\n    public <init>(android.content.Context);\n    public <init>(android.content.Context, android.util.AttributeSet);\n    public <init>(android.content.Context, android.util.AttributeSet, int);\n    public void set*(...);\n} \n\n-keepclasseswithmembernames class * {\n    native <methods>;\n}\n\n-keepclasseswithmembers class * {\n    public <init>(android.content.Context, android.util.AttributeSet);\n}\n\n-keepclasseswithmembers class * {\n    public <init>(android.content.Context, android.util.AttributeSet, int);\n}\n\n-keepclassmembers class * extends android.app.Activity {\n   public void *(android.view.View);\n}\n\n-keepclassmembers enum * {\n    public static **[] values();\n    public static ** valueOf(java.lang.String);\n}\n\n-keep class * implements android.os.Parcelable {\n  public static final android.os.Parcelable$Creator *;\n}\n\n-keep public class roboguice.**\n-keep class com.google.inject.** { *; } \n-keep class javax.inject.** { *; } \n-keep class javax.annotation.** { *; } \n-keep class roboguice.** { *; }\n\n"

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