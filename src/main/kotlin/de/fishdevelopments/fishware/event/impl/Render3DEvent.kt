package de.fishdevelopments.fishware.event.impl

import de.fishdevelopments.fishware.event.Event
import org.joml.Matrix4f

class Render3DEvent(val matrices: Matrix4f, val projectionMatrix: Matrix4f, val state: State) :
  Event() {
  enum class State {
    PRE,
    POST,
  }
}
