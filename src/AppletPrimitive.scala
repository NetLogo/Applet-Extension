package org.nlogo.extensions.applet

import
  java.awt.Component

import
  org.nlogo.{ api, lite, nvm, window },
    api.{ Context, ExtensionException, Primitive },
    lite.Applet,
    nvm.ExtensionContext,
    window.{ GUIWorkspace, InterfacePanelLite }

trait AppletPrimitive {

  self: Primitive =>

  protected def fetchApplet(context: Context) : Applet =
    (extractExtContext _ andThen extractGUIWorkspace _ andThen extractComponent _ andThen extractApplet _)(context)

  protected def extractExtContext(context: Context) : ExtensionContext = {
    context match {
      case extContext: ExtensionContext =>
        extContext
      case _ =>
        throw new ExtensionException("Context somehow wasn't an `ExtensionContext`!")
    }
  }

  protected def extractGUIWorkspace(extContext: ExtensionContext) : GUIWorkspace = {
    extContext.workspace match {
      case guiWorkspace: GUIWorkspace =>
        guiWorkspace
      case _ =>
        throw new ExtensionException("Workspace somehow wasn't an `GUIWorkspace`!")
    }
  }

  protected def extractComponent(guiWorkspace: GUIWorkspace) : Component = {
    guiWorkspace.getWidgetContainer match {
      case panel: InterfacePanelLite =>
        panel
      case _ =>
        throw new ExtensionException("Bad interface panel found; the Applet extension can only be used from within applets!")
    }
  }

  protected def extractApplet(component: Component) : Applet = {

    @scala.annotation.tailrec
    def checkAncestors(comp: Component) : Applet = {
      comp.getParent match {
        case applet: Applet =>
          applet
        case null =>
          throw new ExtensionException("No applet interface component found; the Applet extension can only be used from within applets!")
        case parent =>
          checkAncestors(parent)
      }
    }

    checkAncestors(component)

  }

}

