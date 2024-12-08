package de.fishdevelopments.fishware.mixin

import de.fishdevelopments.fishware.Fishware.Companion.INSTANCE
import de.fishdevelopments.fishware.features.module.impl.visual.OldSwingModule
import de.fishdevelopments.fishware.system.manager.impl.ModuleManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.render.item.HeldItemRenderer
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import org.spongepowered.asm.mixin.Final
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.Redirect
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(HeldItemRenderer::class)
class HeldItemRendererMixin {
  @Shadow @Final private lateinit var client: MinecraftClient

  @Shadow private lateinit var mainHand: ItemStack

  @Shadow private lateinit var offHand: ItemStack

  @Inject(method = ["updateHeldItems"], at = [At("HEAD")])
  fun noswing(ci: CallbackInfo?) {
    val oldSwingModule =
        INSTANCE.managerManager.get(ModuleManager::class.java).get(OldSwingModule::class.java)
    if (oldSwingModule.enabled) {
      val newMainHand = this.client.player!!.mainHandStack
      if (oldSwingModule.canEquipBeIgnored(this.mainHand, this.client.player!!.mainHandStack)) {
        this.mainHand = newMainHand
      }

      val newOffHand = this.client.player!!.offHandStack
      if (oldSwingModule.canEquipBeIgnored(this.offHand, this.client.player!!.offHandStack)) {
        this.offHand = newOffHand
      }
    }
  }

  @Redirect(
      method = ["updateHeldItems"],
      at =
          At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F"))
  fun noswing(instance: ClientPlayerEntity, v: Float): Float {
    val oldSwingModule =
        INSTANCE.managerManager.get(ModuleManager::class.java).get(OldSwingModule::class.java)
    if (oldSwingModule.enabled) {
      return 1f
    }
    return instance.getAttackCooldownProgress(v)
  }

  @Inject(method = ["resetEquipProgress"], at = [At("HEAD")], cancellable = true)
  fun noswing(hand: Hand?, ci: CallbackInfo) {
    val oldSwingModule =
        INSTANCE.managerManager.get(ModuleManager::class.java).get(OldSwingModule::class.java)
    if (oldSwingModule.enabled) {
      ci.cancel()
    }
  }
}
