package de.fishdevelopments.fishware.event.impl

class TickEvent internal constructor(val state: State) {
  enum class State {
    PRE,
    POST,
  }
}
