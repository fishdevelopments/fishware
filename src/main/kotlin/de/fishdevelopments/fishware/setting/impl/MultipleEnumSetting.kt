package de.fishdevelopments.fishware.setting.impl

import de.fishdevelopments.fishware.setting.Setting
import java.util.function.BooleanSupplier

class MultipleEnumSetting<T : Enum<T>> : Setting<Set<T>> {
  val enumClass: Class<T>

  constructor(
    name: String,
    values: Set<T>,
    visibleIf: BooleanSupplier,
  ) : super(name, values, visibleIf) {
    this.enumClass = values.first().javaClass
  }

  constructor(name: String, values: Set<T>) : super(name, values) {
    this.enumClass = values.first().javaClass
  }
}
