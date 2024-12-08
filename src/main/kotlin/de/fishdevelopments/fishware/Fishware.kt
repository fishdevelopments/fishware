package de.fishdevelopments.fishware

class Fishware {
  fun init() {}

  fun shutdown() {}

  companion object {
    val INSTANCE: Fishware = Fishware()
    const val NAME = "fishware"
  }
}
