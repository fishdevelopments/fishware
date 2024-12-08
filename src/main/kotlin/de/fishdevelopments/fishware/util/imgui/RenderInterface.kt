package de.fishdevelopments.fishware.util.imgui

import imgui.ImGuiIO

fun interface RenderInterface {
  fun render(io: ImGuiIO?)
}
