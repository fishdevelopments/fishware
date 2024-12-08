package de.fishdevelopments.fishware.features.module.impl.visual

import de.fishdevelopments.fishware.Fishware
import de.fishdevelopments.fishware.Fishware.Companion.MC
import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.ModuleCategory
import org.lwjgl.glfw.GLFW

class GUIModule : Module(ModuleCategory.VISUAL, "GUI", "Displays a GUI screen.") {

  init {
    this.key = GLFW.GLFW_KEY_RIGHT_SHIFT
  }

  override fun onEnable() {
    super.onEnable()
    this.enabled = false

    if (MC.currentScreen == null) {
      MC.setScreen(Fishware.INSTANCE.guiScreen)
    }
  }
}
