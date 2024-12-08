package de.fishdevelopments.fishware

import me.zero.alpine.bus.EventBus
import me.zero.alpine.bus.EventManager

class Fishware {
  private lateinit var eventBus: EventBus

  fun init() {
    this.eventBus = EventManager.builder().setName("fishware/root").setSuperListeners().build()
  }

  fun shutdown() {}

  companion object {
    val INSTANCE: Fishware = Fishware()
    const val NAME = "fishware"
  }
}
