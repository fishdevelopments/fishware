package de.fishdevelopments.fishware.setting.impl

import de.fishdevelopments.fishware.setting.Setting
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
