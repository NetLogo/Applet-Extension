scalaVersion := "2.9.2"

scalaSource in Compile <<= baseDirectory(_ / "src")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii")

libraryDependencies +=
  "org.nlogo" % "NetLogo" % "5.0.1" from
    "http://ccl.northwestern.edu/netlogo/5.0.1/NetLogo.jar"

name := "applet"

NetLogoExtension.settings

NetLogoExtension.classManager := "org.nlogo.extensions.applet.AppletExtension"
