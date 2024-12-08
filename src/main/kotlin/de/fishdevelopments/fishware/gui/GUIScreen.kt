package de.fishdevelopments.fishware.gui

import de.fishdevelopments.fishware.Fishware
import de.fishdevelopments.fishware.features.module.ModuleCategory
import de.fishdevelopments.fishware.features.module.impl.visual.GUIModule
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager
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

      if (ImGui.beginTabBar("ModuleCategoryTabBar")) {
        for (category in ModuleCategory.entries) {
          if (ImGui.beginTabItem(category.displayName)) {
            for (module in
                Fishware.INSTANCE.managerManager.get(ModuleManager::class.java).getBy {
                  it.category == category
                }) {
              if (ImGui.collapsingHeader(module.name)) {
                ImGui.text(module.description)

                if (module !=
                    Fishware.INSTANCE.managerManager
                        .get(ModuleManager::class.java)
                        .get(GUIModule::class.java)) {
                  if (ImGui.checkbox("Enabled", module.enabled)) {
                    module.toggle()
                  }
                }
              }
            }
            ImGui.endTabItem()
          }
        }
        ImGui.endTabBar()
      }

      ImGui.end()
    }
  }
}
