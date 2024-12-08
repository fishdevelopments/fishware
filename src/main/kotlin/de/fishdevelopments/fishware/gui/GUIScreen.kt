package de.fishdevelopments.fishware.gui

import de.fishdevelopments.fishware.Fishware
import de.fishdevelopments.fishware.features.module.ModuleCategory
import de.fishdevelopments.fishware.features.module.impl.visual.GUIModule
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager
import de.fishdevelopments.fishware.system.setting.impl.BooleanSetting
import de.fishdevelopments.fishware.system.setting.impl.ColorSetting
import de.fishdevelopments.fishware.system.setting.impl.EnumSetting
import de.fishdevelopments.fishware.system.setting.impl.NumberSetting
import de.fishdevelopments.fishware.util.imgui.ImGuiImpl
import imgui.ImGui
import imgui.flag.ImGuiCol
import imgui.flag.ImGuiDir
import imgui.flag.ImGuiSelectableFlags
import imgui.flag.ImGuiWindowFlags
import java.awt.Color
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

class GUIScreen : Screen(Text.literal("GUIScreen")) {

  init {
    val style = ImGui.getStyle()

    style.alpha = 1.0f
    style.disabledAlpha = 0.6000000238418579f
    style.setWindowPadding(8.0f, 8.0f)
    style.windowRounding = 7.0f
    style.windowBorderSize = 1.0f
    style.setWindowMinSize(32.0f, 32.0f)
    style.setWindowTitleAlign(0.0f, 0.5f)
    style.windowMenuButtonPosition = ImGuiDir.Left
    style.childRounding = 4.0f
    style.childBorderSize = 1.0f
    style.popupRounding = 4.0f
    style.popupBorderSize = 1.0f
    style.setFramePadding(5.0f, 2.0f)
    style.frameRounding = 3.0f
    style.frameBorderSize = 1.0f
    style.setItemSpacing(6.0f, 6.0f)
    style.setItemInnerSpacing(6.0f, 6.0f)
    style.setCellPadding(6.0f, 6.0f)
    style.indentSpacing = 25.0f
    style.columnsMinSpacing = 6.0f
    style.scrollbarSize = 15.0f
    style.scrollbarRounding = 9.0f
    style.grabMinSize = 10.0f
    style.grabRounding = 3.0f
    style.tabRounding = 4.0f
    style.tabBorderSize = 1.0f
    style.tabMinWidthForCloseButton = 0.0f
    style.colorButtonPosition = ImGuiDir.Right
    style.setButtonTextAlign(0.5f, 0.5f)
    style.setSelectableTextAlign(0.0f, 0.0f)

    style.setColor(ImGuiCol.Text, 1.0f, 1.0f, 1.0f, 1.0f)
    style.setColor(
      ImGuiCol.TextDisabled,
      0.4980392158031464f,
      0.4980392158031464f,
      0.4980392158031464f,
      1.0f,
    )
    style.setColor(
      ImGuiCol.WindowBg,
      0.09803921729326248f,
      0.09803921729326248f,
      0.09803921729326248f,
      0.9f,
    )
    style.setColor(ImGuiCol.ChildBg, 0.0f, 0.0f, 0.0f, 0.0f)
    style.setColor(
      ImGuiCol.PopupBg,
      0.1882352977991104f,
      0.1882352977991104f,
      0.1882352977991104f,
      0.9200000166893005f,
    )
    style.setColor(
      ImGuiCol.Border,
      0.1882352977991104f,
      0.1882352977991104f,
      0.1882352977991104f,
      0.2899999916553497f,
    )
    style.setColor(ImGuiCol.BorderShadow, 0.0f, 0.0f, 0.0f, 0.239999994635582f)
    style.setColor(
      ImGuiCol.FrameBg,
      0.0470588244497776f,
      0.0470588244497776f,
      0.0470588244497776f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.FrameBgHovered,
      0.1882352977991104f,
      0.1882352977991104f,
      0.1882352977991104f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.FrameBgActive,
      0.2000000029802322f,
      0.2196078449487686f,
      0.2274509817361832f,
      1.0f,
    )
    style.setColor(ImGuiCol.TitleBg, 0.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(
      ImGuiCol.TitleBgActive,
      0.05882352963089943f,
      0.05882352963089943f,
      0.05882352963089943f,
      1.0f,
    )
    style.setColor(ImGuiCol.TitleBgCollapsed, 0.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(
      ImGuiCol.MenuBarBg,
      0.1372549086809158f,
      0.1372549086809158f,
      0.1372549086809158f,
      1.0f,
    )
    style.setColor(
      ImGuiCol.ScrollbarBg,
      0.0470588244497776f,
      0.0470588244497776f,
      0.0470588244497776f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.ScrollbarGrab,
      0.3372549116611481f,
      0.3372549116611481f,
      0.3372549116611481f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.ScrollbarGrabHovered,
      0.4000000059604645f,
      0.4000000059604645f,
      0.4000000059604645f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.ScrollbarGrabActive,
      0.5568627715110779f,
      0.5568627715110779f,
      0.5568627715110779f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.CheckMark,
      0.3294117748737335f,
      0.6666666865348816f,
      0.8588235378265381f,
      1.0f,
    )
    style.setColor(
      ImGuiCol.SliderGrab,
      0.3372549116611481f,
      0.3372549116611481f,
      0.3372549116611481f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.SliderGrabActive,
      0.5568627715110779f,
      0.5568627715110779f,
      0.5568627715110779f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.Button,
      0.0470588244497776f,
      0.0470588244497776f,
      0.0470588244497776f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.ButtonHovered,
      0.1882352977991104f,
      0.1882352977991104f,
      0.1882352977991104f,
      0.5400000214576721f,
    )
    style.setColor(
      ImGuiCol.ButtonActive,
      0.2000000029802322f,
      0.2196078449487686f,
      0.2274509817361832f,
      1.0f,
    )
    style.setColor(ImGuiCol.Header, 0.0f, 0.0f, 0.0f, 0.5199999809265137f)
    style.setColor(ImGuiCol.HeaderHovered, 0.0f, 0.0f, 0.0f, 0.3600000143051147f)
    style.setColor(
      ImGuiCol.HeaderActive,
      0.2000000029802322f,
      0.2196078449487686f,
      0.2274509817361832f,
      0.3300000131130219f,
    )
    style.setColor(
      ImGuiCol.Separator,
      0.2784313857555389f,
      0.2784313857555389f,
      0.2784313857555389f,
      0.2899999916553497f,
    )
    style.setColor(
      ImGuiCol.SeparatorHovered,
      0.4392156898975372f,
      0.4392156898975372f,
      0.4392156898975372f,
      0.2899999916553497f,
    )
    style.setColor(
      ImGuiCol.SeparatorActive,
      0.4000000059604645f,
      0.4392156898975372f,
      0.4666666686534882f,
      1.0f,
    )
    style.setColor(
      ImGuiCol.ResizeGrip,
      0.2784313857555389f,
      0.2784313857555389f,
      0.2784313857555389f,
      0.2899999916553497f,
    )
    style.setColor(
      ImGuiCol.ResizeGripHovered,
      0.4392156898975372f,
      0.4392156898975372f,
      0.4392156898975372f,
      0.2899999916553497f,
    )
    style.setColor(
      ImGuiCol.ResizeGripActive,
      0.4000000059604645f,
      0.4392156898975372f,
      0.4666666686534882f,
      1.0f,
    )
    style.setColor(ImGuiCol.Tab, 0.0f, 0.0f, 0.0f, 0.5199999809265137f)
    style.setColor(
      ImGuiCol.TabHovered,
      0.1372549086809158f,
      0.1372549086809158f,
      0.1372549086809158f,
      1.0f,
    )
    style.setColor(
      ImGuiCol.TabActive,
      0.2000000029802322f,
      0.2000000029802322f,
      0.2000000029802322f,
      0.3600000143051147f,
    )
    style.setColor(ImGuiCol.TabUnfocused, 0.0f, 0.0f, 0.0f, 0.5199999809265137f)
    style.setColor(
      ImGuiCol.TabUnfocusedActive,
      0.1372549086809158f,
      0.1372549086809158f,
      0.1372549086809158f,
      1.0f,
    )
    style.setColor(ImGuiCol.PlotLines, 1.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(ImGuiCol.PlotLinesHovered, 1.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(ImGuiCol.PlotHistogram, 1.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(ImGuiCol.PlotHistogramHovered, 1.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(ImGuiCol.TableHeaderBg, 0.0f, 0.0f, 0.0f, 0.5199999809265137f)
    style.setColor(ImGuiCol.TableBorderStrong, 0.0f, 0.0f, 0.0f, 0.5199999809265137f)
    style.setColor(
      ImGuiCol.TableBorderLight,
      0.2784313857555389f,
      0.2784313857555389f,
      0.2784313857555389f,
      0.2899999916553497f,
    )
    style.setColor(ImGuiCol.TableRowBg, 0.0f, 0.0f, 0.0f, 0.0f)
    style.setColor(ImGuiCol.TableRowBgAlt, 1.0f, 1.0f, 1.0f, 0.05999999865889549f)
    style.setColor(
      ImGuiCol.TextSelectedBg,
      0.2000000029802322f,
      0.2196078449487686f,
      0.2274509817361832f,
      1.0f,
    )
    style.setColor(
      ImGuiCol.DragDropTarget,
      0.3294117748737335f,
      0.6666666865348816f,
      0.8588235378265381f,
      1.0f,
    )
    style.setColor(ImGuiCol.NavHighlight, 1.0f, 0.0f, 0.0f, 1.0f)
    style.setColor(ImGuiCol.NavWindowingHighlight, 1.0f, 0.0f, 0.0f, 0.699999988079071f)
    style.setColor(ImGuiCol.NavWindowingDimBg, 1.0f, 0.0f, 0.0f, 0.2000000029802322f)
    style.setColor(ImGuiCol.ModalWindowDimBg, 1.0f, 0.0f, 0.0f, 0.3499999940395355f)
  }

  override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
    // super.render(context, mouseX, mouseY, delta)

    ImGuiImpl.draw {
      ImGui.begin(Fishware.NAME, ImGuiWindowFlags.NoCollapse)

      if (ImGui.beginTabBar("ModuleCategoryTabBar")) {
        for (category in ModuleCategory.entries) {
          if (ImGui.beginTabItem(category.displayName)) {
            for (module in
              Fishware.INSTANCE.managerManager.get(ModuleManager::class.java).getBy {
                it.category == category
              }) {
              if (ImGui.collapsingHeader(module.name)) {
                ImGui.text(module.description)

                ImGui.pushID(module.name)
                if (
                  module !=
                    Fishware.INSTANCE.managerManager
                      .get(ModuleManager::class.java)
                      .get(GUIModule::class.java)
                ) {
                  if (ImGui.checkbox("Enabled", module.enabled)) {
                    module.toggle()
                  }

                  for (setting in module.settings) {
                    if (!setting.isVisible()) {
                      continue
                    }

                    if (setting is BooleanSetting) {
                      if (ImGui.checkbox(setting.name, setting.value)) {
                        setting.toggle()
                      }
                    } else if (setting is ColorSetting) {
                      val value = setting.value
                      val color =
                        floatArrayOf(
                          value.red / 255f,
                          value.green / 255f,
                          value.blue / 255f,
                          value.alpha / 255f,
                        )
                      if (ImGui.colorEdit4(setting.name, color)) {
                        setting.value =
                          Color(
                            (color[0] * 255).toInt(),
                            (color[1] * 255).toInt(),
                            (color[2] * 255).toInt(),
                            (color[3] * 255).toInt(),
                          )
                      }
                    } else if (setting is EnumSetting<*>) {
                      if (
                        ImGui.beginCombo(
                          setting.name,
                          setting.value.joinToString(", ") { it.toString() },
                        )
                      ) {
                        for (enumConstant in setting.enumClass.enumConstants) {
                          val selected = setting.value.contains(enumConstant)
                          if (
                            ImGui.selectable(
                              enumConstant.toString(),
                              selected,
                              ImGuiSelectableFlags.DontClosePopups,
                            )
                          ) {
                            if (setting.multiple) {
                              val newValue = setting.value.toMutableSet()
                              if (selected) {
                                newValue.remove(enumConstant)
                              } else {
                                newValue.add(enumConstant)
                              }
                              setting.setValueAsAny(newValue)
                            } else {
                              setting.setValueAsAny(setOf(enumConstant))
                            }
                          }
                        }
                        ImGui.endCombo()
                      }
                    } else if (setting is NumberSetting) {
                      if (setting.value is Float) {
                        val value = floatArrayOf(setting.value as Float)
                        if (
                          ImGui.sliderFloat(
                            setting.name,
                            value,
                            setting.minValue as Float,
                            setting.maxValue as Float,
                          )
                        ) {
                          setting.setValueAsAny(value[0])
                        }
                      }
                      // no ImGui.sliderDouble
                      else if (setting.value is Double) {
                        val value = floatArrayOf((setting.value as Double).toFloat())
                        if (
                          ImGui.sliderFloat(
                            setting.name,
                            value,
                            (setting.minValue as Double).toFloat(),
                            (setting.maxValue as Double).toFloat(),
                          )
                        ) {
                          setting.setValueAsAny(value[0].toDouble())
                        }
                      } else {
                        val value = intArrayOf(setting.value as Int)
                        if (
                          ImGui.sliderInt(
                            setting.name,
                            value,
                            setting.minValue as Int,
                            setting.maxValue as Int,
                          )
                        ) {
                          setting.setValueAsAny(value[0])
                        }
                      }
                    }
                  }
                }
                ImGui.popID()
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

  override fun shouldPause(): Boolean {
    return false
  }
}
