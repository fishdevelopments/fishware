package de.fishdevelopments.fishware.mixin

import de.fishdevelopments.fishware.Fishware
import net.minecraft.client.MinecraftClient
import net.minecraft.client.RunArgs
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(MinecraftClient::class)
class MinecraftClientMixin {
  @Inject(method = ["<init>"], at = [At("RETURN")])
  fun fishware_init(args: RunArgs?, ci: CallbackInfo?) {
    Fishware.INSTANCE.init()
  }

  @Inject(method = ["close"], at = [At("HEAD")])
  fun fishware_shutdown(ci: CallbackInfo?) {
    Fishware.INSTANCE.shutdown()
  }
}
