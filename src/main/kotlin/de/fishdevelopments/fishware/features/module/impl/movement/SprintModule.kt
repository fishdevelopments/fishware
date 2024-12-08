package de.fishdevelopments.fishware.features.module.impl.movement

import de.fishdevelopments.fishware.Fishware.Companion.MC
import de.fishdevelopments.fishware.event.impl.TickEvent
import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.ModuleCategory
import de.fishdevelopments.fishware.system.setting.impl.BooleanSetting
import de.fishdevelopments.fishware.system.setting.impl.ColorSetting
import de.fishdevelopments.fishware.system.setting.impl.EnumSetting
import de.fishdevelopments.fishware.system.setting.impl.NumberSetting
import java.awt.Color
import me.zero.alpine.listener.Subscribe

class SprintModule : Module(ModuleCategory.MOVEMENT, "Sprint", "Automatically sprints.") {

  enum class Mode {
    INTAVE,
    POLAR
  }

  private val booleanSetting = BooleanSetting("Boolean", true)
  private val colorSetting = ColorSetting("Color", Color.RED)
  private val enumSetting = EnumSetting("Mode", Mode.INTAVE)
  private val numberSetting: NumberSetting<Float> = NumberSetting("Number", 10f, 0f, 20f)

  init {
    this.settings.add(this.booleanSetting)
    this.settings.add(this.colorSetting)
    this.settings.add(this.enumSetting)
    this.settings.add(this.numberSetting)
  }

  @Subscribe
  fun onTickEvent(tickEvent: TickEvent) {
    if (tickEvent.state == TickEvent.State.PRE) {
      MC.options.sprintKey.isPressed = true
    }
  }
}
