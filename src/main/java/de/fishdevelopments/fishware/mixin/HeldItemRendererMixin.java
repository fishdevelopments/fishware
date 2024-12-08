package de.fishdevelopments.fishware.mixin;

import de.fishdevelopments.fishware.Fishware;
import de.fishdevelopments.fishware.features.module.impl.visual.OldSwingModule;
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
  @Shadow @Final private MinecraftClient client;
  @Shadow private ItemStack mainHand;
  @Shadow private ItemStack offHand;

  @Inject(method = "updateHeldItems", at = @At("HEAD"))
  public void oldswing(CallbackInfo ci) {
    OldSwingModule oldSwingModule =
        Fishware.Companion.getINSTANCE()
            .getManagerManager()
            .get(ModuleManager.class)
            .get(OldSwingModule.class);
    if (oldSwingModule.getEnabled()) {
      ItemStack newMainHand = this.client.player.getMainHandStack();
      if (oldSwingModule.canEquipBeIgnored(this.mainHand, this.client.player.getMainHandStack())) {
        this.mainHand = newMainHand;
      }

      ItemStack newOffHand = this.client.player.getOffHandStack();
      if (oldSwingModule.canEquipBeIgnored(this.offHand, this.client.player.getOffHandStack())) {
        this.offHand = newOffHand;
      }
    }
  }

  @Redirect(
      method = "updateHeldItems",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F"))
  public float oldswing(ClientPlayerEntity instance, float v) {
    OldSwingModule oldSwingModule =
        Fishware.Companion.getINSTANCE()
            .getManagerManager()
            .get(ModuleManager.class)
            .get(OldSwingModule.class);
    if (oldSwingModule.getEnabled()) {
      return 1f;
    }
    return instance.getAttackCooldownProgress(v);
  }

  @Inject(method = "resetEquipProgress", at = @At("HEAD"), cancellable = true)
  public void oldswing(Hand hand, CallbackInfo ci) {
    OldSwingModule oldSwingModule =
        Fishware.Companion.getINSTANCE()
            .getManagerManager()
            .get(ModuleManager.class)
            .get(OldSwingModule.class);
    if (oldSwingModule.getEnabled()) {
      ci.cancel();
    }
  }
}
