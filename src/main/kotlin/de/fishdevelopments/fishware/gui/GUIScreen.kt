package de.fishdevelopments.fishware.gui

import de.fishdevelopments.fishware.Fishware
import de.fishdevelopments.fishware.util.imgui.ImGuiImpl
import imgui.ImGui
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

class GUIScreen : Screen(Text.literal("GUIScreen")) {
  override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
    // super.render(context, mouseX, mouseY, delta)

    ImGuiImpl.draw {
      ImGui.begin(Fishware.NAME)
      ImGui.end()
    }
  }
}
