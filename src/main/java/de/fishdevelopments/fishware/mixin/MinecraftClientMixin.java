package de.fishdevelopments.fishware.mixin;

import de.fishdevelopments.fishware.Fishware;
import de.fishdevelopments.fishware.event.impl.TickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
  @Inject(method = "<init>", at = @At("RETURN"))
  public void fishware_init(RunArgs args, CallbackInfo ci) {
    Fishware.Companion.getINSTANCE().init();
  }

  @Inject(method = "close", at = @At("HEAD"))
  public void fishware_shutdown(CallbackInfo ci) {
    Fishware.Companion.getINSTANCE().shutdown();
  }

  @Inject(method = "tick", at = @At("HEAD"))
  public void post_tickevent_pre(CallbackInfo ci) {
    Fishware.Companion.getINSTANCE().getEventBus().post(new TickEvent(TickEvent.State.PRE));
  }

  @Inject(method = "tick", at = @At("TAIL"))
  public void post_tickevent_post(CallbackInfo ci) {
    Fishware.Companion.getINSTANCE().getEventBus().post(new TickEvent(TickEvent.State.POST));
  }
}
