package org.nlogo.extensions.applet

import
  org.nlogo.api.{ Argument, Context, DefaultClassManager, DefaultReporter, PrimitiveManager, Syntax },
      Syntax.{ reporterSyntax, StringType }

class AppletExtension extends DefaultClassManager {
  def load(primitiveManager: PrimitiveManager) {
    primitiveManager.addPrimitive("get", Get)
  }
}

object Get extends DefaultReporter with AppletPrimitive {
  override def getSyntax = reporterSyntax(Array(StringType), StringType)
  override def report(args: Array[Argument], context: Context) : AnyRef = {
    val argName = args(0).getString
    val applet  = fetchApplet(context)
    Option(applet.getParameter(argName)) getOrElse Boolean.box(false)
  }
}
