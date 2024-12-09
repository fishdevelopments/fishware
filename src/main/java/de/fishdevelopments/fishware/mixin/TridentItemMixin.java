package de.fishdevelopments.fishware.mixin;

import de.fishdevelopments.fishware.Fishware;
import de.fishdevelopments.fishware.features.module.impl.exploit.TridentBoostModule;
import de.fishdevelopments.fishware.manager.impl.ModuleManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TridentItem.class)
public class TridentItemMixin {
  @ModifyArgs(
      method = "onStoppedUsing",
      at =
          @At(
              value = "INVOKE",
              target = "Lnet/minecraft/entity/player/PlayerEntity;addVelocity(DDD)V"))
  public void tridentboost_multiplier(Args args) {
    TridentBoostModule tridentBoostModule =
        Fishware.Companion.getINSTANCE()
            .getManagerManager()
            .get(ModuleManager.class)
            .get(TridentBoostModule.class);
    if (tridentBoostModule.getEnabled()) {
      double multiplier = tridentBoostModule.getMultiplierSetting().getValue();
      args.set(0, (double) args.get(0) * multiplier);
      args.set(1, (double) args.get(1) * multiplier);
      args.set(2, (double) args.get(2) * multiplier);
    }
  }

  @Redirect(
      method = {"use", "onStoppedUsing"},
      at =
          @At(
              value = "INVOKE",
              target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"))
  public boolean tridentboost_allowoutofwater(PlayerEntity instance) {
    TridentBoostModule tridentBoostModule =
        Fishware.Companion.getINSTANCE()
            .getManagerManager()
            .get(ModuleManager.class)
            .get(TridentBoostModule.class);
    if (tridentBoostModule.getEnabled()
        && tridentBoostModule.getAllowOutOfWaterSetting().getValue()) {
      return true;
    }
    return instance.isTouchingWaterOrRain();
  }
}
