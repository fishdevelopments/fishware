package de.fishdevelopments.fishware

import de.fishdevelopments.fishware.system.manager.impl.ManagerManager
import me.zero.alpine.bus.EventBus
import me.zero.alpine.bus.EventManager
import net.minecraft.client.MinecraftClient

class Fishware {
  lateinit var eventBus: EventBus
  lateinit var managerManager: ManagerManager

  fun init() {
    this.eventBus = EventManager.builder().setName("fishware/root").setSuperListeners().build()
    this.managerManager = ManagerManager()
    this.managerManager.run()
  }

  fun shutdown() {}

  companion object {
    val INSTANCE: Fishware = Fishware()
    val MC: MinecraftClient = MinecraftClient.getInstance()
    const val NAME = "fishware"
  }
}
