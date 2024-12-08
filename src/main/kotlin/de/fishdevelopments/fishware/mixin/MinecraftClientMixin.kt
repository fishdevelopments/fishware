package de.fishdevelopments.fishware.mixin

import de.fishdevelopments.fishware.Fishware
import de.fishdevelopments.fishware.event.impl.TickEvent
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

  @Inject(method = ["tick"], at = [At("HEAD")])
  fun post_tickevent_pre(ci: CallbackInfo?) {
    Fishware.INSTANCE.eventBus.post(TickEvent(TickEvent.State.PRE))
  }

  @Inject(method = ["tick"], at = [At("TAIL")])
  fun post_tickevent_post(ci: CallbackInfo?) {
    Fishware.INSTANCE.eventBus.post(TickEvent(TickEvent.State.POST))
  }
}
