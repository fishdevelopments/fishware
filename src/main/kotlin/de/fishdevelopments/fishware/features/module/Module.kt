package de.fishdevelopments.fishware.features.module

import de.fishdevelopments.fishware.Fishware.Companion.INSTANCE
import de.fishdevelopments.fishware.system.setting.Setting
import me.zero.alpine.listener.Subscriber

abstract class Module
protected constructor(val category: ModuleCategory, val name: String, val description: String) :
    Subscriber {
  var enabled: Boolean = false
    set(value) {
      if (value != field) {
        field = value
        if (value) {
          this.onEnable()
        } else {
          this.onDisable()
        }
      }
    }

  var key: Int = 0

  val settings: MutableList<Setting<*>> = mutableListOf()

  fun toggle() {
    this.enabled = !this.enabled
  }

  open fun onEnable() {
    INSTANCE.eventBus.subscribe(this)
  }

  open fun onDisable() {
    INSTANCE.eventBus.unsubscribe(this)
  }
}
