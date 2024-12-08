package de.fishdevelopments.fishware.system.manager.impl

import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.impl.movement.SprintModule
import de.fishdevelopments.fishware.system.manager.ClassInstanceManager

class ModuleManager : ClassInstanceManager<Module>() {

  override fun run() {
    this.register(SprintModule::class.java)
  }
}
