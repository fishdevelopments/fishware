package de.fishdevelopments.fishware.system.setting.impl

import de.fishdevelopments.fishware.system.setting.Setting
import java.awt.Color
import java.util.function.BooleanSupplier

class ColorSetting : Setting<Color> {
  constructor(
    name: String,
    value: Color,
    visibleIf: BooleanSupplier,
  ) : super(name, value, visibleIf)

  constructor(name: String, value: Color) : super(name, value)
}
