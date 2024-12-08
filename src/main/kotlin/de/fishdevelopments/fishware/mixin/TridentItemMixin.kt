package de.fishdevelopments.fishware.mixin

import de.fishdevelopments.fishware.Fishware.Companion.INSTANCE
import de.fishdevelopments.fishware.features.module.impl.exploit.TridentBoostModule
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.TridentItem
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyArgs
import org.spongepowered.asm.mixin.injection.Redirect
import org.spongepowered.asm.mixin.injection.invoke.arg.Args

@Mixin(TridentItem::class)
class TridentItemMixin {
  @ModifyArgs(
      method = ["onStoppedUsing"],
      at =
          At(
              value = "INVOKE",
              target = "Lnet/minecraft/entity/player/PlayerEntity;addVelocity(DDD)V"))
  fun tridentboost_multiplier(args: Args) {
    val tridentBoostModule =
        INSTANCE.managerManager.get(ModuleManager::class.java).get(TridentBoostModule::class.java)
    if (tridentBoostModule.enabled) {
      val multiplier = tridentBoostModule.multiplierSetting.value
      args.set(0, args.get(0) as Double * multiplier)
      args.set(1, args.get(1) as Double * multiplier)
      args.set(2, args.get(2) as Double * multiplier)
    }
  }

  @Redirect(
      method = ["use", "onStoppedUsing"],
      at =
          At(
              value = "INVOKE",
              target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"))
  fun tridentboost_allowoutofwater(instance: PlayerEntity): Boolean {
    val tridentBoostModule =
        INSTANCE.managerManager.get(ModuleManager::class.java).get(TridentBoostModule::class.java)
    if (tridentBoostModule.enabled && tridentBoostModule.allowOutOfWaterSetting.value) {
      return true
    }
    return instance.isTouchingWaterOrRain
  }
}
