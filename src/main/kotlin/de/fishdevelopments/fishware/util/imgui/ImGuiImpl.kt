package de.fishdevelopments.fishware.util.imgui

import imgui.ImGui
import imgui.extension.implot.ImPlot
import imgui.extension.implot.ImPlotContext
import imgui.flag.ImGuiConfigFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw

object ImGuiImpl {
  private val imGuiImplGlfw = ImGuiImplGlfw()
  private val imGuiImplGl3 = ImGuiImplGl3()

  private lateinit var imPlotContext: ImPlotContext

  fun create(handle: Long) {
    ImGui.createContext()
    this.imPlotContext = ImPlot.createContext()
    val data = ImGui.getIO()
    data.iniFilename = "fishware.ini"
    data.fontGlobalScale = 1f
    data.configFlags = ImGuiConfigFlags.DockingEnable
    this.imGuiImplGlfw.init(handle, true)
    this.imGuiImplGl3.init()
  }

  fun draw(runnable: RenderInterface) {
    this.imGuiImplGlfw.newFrame()
    ImGui.newFrame()
    runnable.render(ImGui.getIO())
    ImGui.render()
    this.imGuiImplGl3.renderDrawData(ImGui.getDrawData())
  }

  fun dispose() {
    this.imGuiImplGl3.dispose()
    ImGui.destroyContext()
    ImPlot.destroyContext(this.imPlotContext)
  }
}
