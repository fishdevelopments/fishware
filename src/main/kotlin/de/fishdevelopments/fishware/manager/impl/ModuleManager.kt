package de.fishdevelopments.fishware.manager.impl

import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.impl.exploit.TridentBoostModule
import de.fishdevelopments.fishware.features.module.impl.movement.SprintModule
import de.fishdevelopments.fishware.features.module.impl.visual.ESPModule
import de.fishdevelopments.fishware.features.module.impl.visual.GUIModule
import de.fishdevelopments.fishware.features.module.impl.visual.OldSwingModule
import de.fishdevelopments.fishware.manager.ClassInstanceManager

class ModuleManager : ClassInstanceManager<Module>() {

  override fun run() {
    // combat

    // exploit
    this.register(TridentBoostModule::class.java)
    // fun

    // movement
    this.register(SprintModule::class.java)

    // player

    // visual
    this.register(ESPModule::class.java)
    this.register(GUIModule::class.java)
    this.register(OldSwingModule::class.java)
  }
}
