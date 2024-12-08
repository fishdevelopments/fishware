package de.fishdevelopments.fishware.mixin;

import de.fishdevelopments.fishware.Fishware;
import de.fishdevelopments.fishware.features.module.Module;
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
  @Inject(method = "onKey", at = @At("HEAD"))
  public void handle_keyboard(
      long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
    for (Module module :
        Fishware.Companion.getINSTANCE().getManagerManager().get(ModuleManager.class).getValues()) {
      if (action == GLFW.GLFW_PRESS) {
        if (module.getKey() == key) {
          module.toggle();
        }
      }
    }
  }
}
