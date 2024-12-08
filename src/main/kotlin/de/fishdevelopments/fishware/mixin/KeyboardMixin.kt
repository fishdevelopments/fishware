package de.fishdevelopments.fishware.mixin

import de.fishdevelopments.fishware.Fishware.Companion.INSTANCE
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager
import java.util.*
import net.minecraft.client.Keyboard
import org.lwjgl.glfw.GLFW
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(Keyboard::class)
class KeyboardMixin {
  @Inject(method = ["onKey"], at = [At("HEAD")])
  fun handle_keyboard(
      window: Long,
      key: Int,
      scancode: Int,
      action: Int,
      modifiers: Int,
      ci: CallbackInfo?
  ) {
    for (module in
        Objects.requireNonNull(INSTANCE.managerManager.get(ModuleManager::class.java))
            ?.getValues()!!) {
      if (action == GLFW.GLFW_PRESS) {
        if (module.key == key) {
          module.toggle()
        }
      }
    }
  }
}
