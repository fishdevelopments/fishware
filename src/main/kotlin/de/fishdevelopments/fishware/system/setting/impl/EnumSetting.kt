package de.fishdevelopments.fishware.system.setting.impl

import de.fishdevelopments.fishware.system.setting.Setting
import java.util.function.BooleanSupplier

class EnumSetting<T : Enum<T>> : Setting<Set<T>> {
  val enumClass: Class<T>
  val multiple: Boolean

  constructor(
    name: String,
    value: T,
    visibleIf: BooleanSupplier,
    multiple: Boolean = false,
  ) : super(name, setOf(value), visibleIf) {
    this.enumClass = value.javaClass
    this.multiple = multiple
  }

  constructor(name: String, value: T, multiple: Boolean = false) : super(name, setOf(value)) {
    this.enumClass = value.javaClass
    this.multiple = multiple
  }

  constructor(
    name: String,
    values: Set<T>,
    visibleIf: BooleanSupplier,
    multiple: Boolean = true,
  ) : super(name, values, visibleIf) {
    this.enumClass = values.first().javaClass
    this.multiple = multiple
  }

  constructor(name: String, values: Set<T>, multiple: Boolean = true) : super(name, values) {
    this.enumClass = values.first().javaClass
    this.multiple = multiple
  }
}
