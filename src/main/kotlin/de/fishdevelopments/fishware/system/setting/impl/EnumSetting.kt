package de.fishdevelopments.fishware.system.setting.impl

import de.fishdevelopments.fishware.system.setting.Setting
import java.util.function.BooleanSupplier

class EnumSetting<T : Enum<T>> : Setting<T> {
  val enumClass: Class<T>

  constructor(name: String, value: T, visibleIf: BooleanSupplier) : super(name, value, visibleIf) {
    this.enumClass = value.javaClass
  }

  constructor(name: String, value: T) : super(name, value) {
    this.enumClass = value.javaClass
  }
}
