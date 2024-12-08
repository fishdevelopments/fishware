package de.fishdevelopments.fishware

import de.fishdevelopments.fishware.system.manager.impl.ManagerManager
import de.fishdevelopments.fishware.util.imgui.ImGuiImpl
import me.zero.alpine.bus.EventBus
import me.zero.alpine.bus.EventManager
import net.minecraft.client.MinecraftClient

class Fishware {
  lateinit var eventBus: EventBus
  lateinit var imguiImpl: ImGuiImpl
  lateinit var managerManager: ManagerManager

  fun init() {
    this.eventBus = EventManager.builder().setName("fishware/root").setSuperListeners().build()
    this.imguiImpl.create(MC.window.handle)
    this.managerManager = ManagerManager()
    this.managerManager.run()
  }

  fun shutdown() {
    this.imguiImpl.dispose()
  }

  companion object {
    val INSTANCE: Fishware = Fishware()
    val MC: MinecraftClient = MinecraftClient.getInstance()
    const val NAME = "fishware"
  }
}
