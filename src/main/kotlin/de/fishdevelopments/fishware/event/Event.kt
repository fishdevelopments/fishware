package de.fishdevelopments.fishware.event

abstract class Event {
  var canceled = false

  fun cancel() {
    this.canceled = true
  }
}
