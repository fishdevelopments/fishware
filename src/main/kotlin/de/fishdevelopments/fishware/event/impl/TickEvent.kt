package de.fishdevelopments.fishware.event.impl

import de.fishdevelopments.fishware.event.Event

class TickEvent internal constructor(val state: State) : Event() {
  enum class State {
    PRE,
    POST,
  }
}
