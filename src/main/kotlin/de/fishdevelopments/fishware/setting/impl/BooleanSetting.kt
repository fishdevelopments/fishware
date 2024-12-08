package de.fishdevelopments.fishware.setting.impl

import de.fishdevelopments.fishware.setting.Setting
import java.util.function.BooleanSupplier

class BooleanSetting : Setting<Boolean> {
  constructor(
    name: String,
    value: Boolean,
    visibleIf: BooleanSupplier,
  ) : super(name, value, visibleIf)

  constructor(name: String, value: Boolean) : super(name, value)

  fun toggle() {
    this.value = !this.value
  }
}
