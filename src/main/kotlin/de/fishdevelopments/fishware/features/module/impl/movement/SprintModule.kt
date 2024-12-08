package de.fishdevelopments.fishware.features.module.impl.movement

import de.fishdevelopments.fishware.Fishware.Companion.MC
import de.fishdevelopments.fishware.event.impl.TickEvent
import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.ModuleCategory
import me.zero.alpine.listener.Subscribe

class SprintModule : Module(ModuleCategory.MOVEMENT, "Sprint", "Automatically sprints.") {

  init {
    this.enabled = true
  }

  @Subscribe
  fun onTickEvent(tickEvent: TickEvent) {
    if (tickEvent.state == TickEvent.State.PRE) {
      MC.options.sprintKey.isPressed = true
    }
  }
}
