scalaVersion := "2.9.2"

scalaSource in Compile <<= baseDirectory(_ / "src")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii")

libraryDependencies +=
  "org.nlogo" % "NetLogo" % "5.0.1" from
    "http://ccl.northwestern.edu/netlogo/5.0.1/NetLogo.jar"

artifactName := { (_, _, _) => "applet.jar" }

packageOptions := Seq(
  Package.ManifestAttributes(
    ("Extension-Name", "applet"),
    ("Class-Manager", "org.nlogo.extensions.applet.AppletExtension"),
    ("NetLogo-Extension-API-Version", "5.0")))

packageBin in Compile <<= (packageBin in Compile, baseDirectory, streams) map {
  (jar, base, s) =>
    IO.copyFile(jar, base / "applet.jar")
    Process("pack200 --modification-time=latest --effort=9 --strip-debug " +
            "--no-keep-file-order --unknown-attribute=strip " +
            "applet.jar.pack.gz applet.jar").!!
    if(Process("git diff --quiet --exit-code HEAD").! == 0) {
      Process("git archive -o applet.zip --prefix=applet/ HEAD").!!
      IO.createDirectory(base / "applet")
      IO.copyFile(base / "applet.jar", base / "applet" / "applet.jar")
      IO.copyFile(base / "applet.jar.pack.gz", base / "applet" / "applet.jar.pack.gz")
      Process("zip applet.zip applet/applet.jar applet/applet.jar.pack.gz").!!
      IO.delete(base / "applet")
    }
    else {
      s.log.warn("working tree not clean; no zip archive made")
      IO.delete(base / "applet.zip")
    }
    jar
  }

cleanFiles <++= baseDirectory { base =>
  Seq(base / "applet.jar",
      base / "applet.jar.pack.gz",
      base / "applet.zip") }

