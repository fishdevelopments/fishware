package de.fishdevelopments.fishware.system.setting

import java.util.function.BooleanSupplier

abstract class Setting<T>
@JvmOverloads
internal constructor(
    val name: String,
    var value: T,
    var visibleIf: BooleanSupplier = BooleanSupplier { true }
) {
  fun setValueAsAny(value: Any) {
    @Suppress("UNCHECKED_CAST")
    this.value = value as T
  }
}
