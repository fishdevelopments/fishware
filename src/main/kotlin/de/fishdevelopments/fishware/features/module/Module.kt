package de.fishdevelopments.fishware.features.module

import de.fishdevelopments.fishware.Fishware.Companion.INSTANCE
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
