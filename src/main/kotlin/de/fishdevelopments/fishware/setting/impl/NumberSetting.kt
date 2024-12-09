package de.fishdevelopments.fishware.setting.impl

import de.fishdevelopments.fishware.setting.Setting
import java.util.function.BooleanSupplier

class NumberSetting<T : Number> : Setting<T> {
  val minValue: T
  val maxValue: T

  constructor(
    name: String,
    value: T,
    visibleIf: BooleanSupplier,
    minValue: T,
    maxValue: T,
  ) : super(name, value, visibleIf) {
    this.minValue = minValue
    this.maxValue = maxValue
  }

  constructor(name: String, value: T, minValue: T, maxValue: T) : super(name, value) {
    this.minValue = minValue
    this.maxValue = maxValue
  }
}
