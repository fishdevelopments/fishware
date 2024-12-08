package de.fishdevelopments.fishware.system.manager.impl

import de.fishdevelopments.fishware.system.manager.ClassInstanceManager

class ManagerManager : ClassInstanceManager<ClassInstanceManager<*>>() {

  override fun run() {
    this.register(ModuleManager::class.java)

    for (manager in this.getValues()) {
      manager.run()
    }
  }
}
