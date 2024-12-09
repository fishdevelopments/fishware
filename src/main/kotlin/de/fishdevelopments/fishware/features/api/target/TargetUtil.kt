package de.fishdevelopments.fishware.features.api.target

import de.fishdevelopments.fishware.setting.impl.MultipleEnumSetting
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity

object TargetUtil {

  fun isTarget(livingEntity: LivingEntity, targetSetting: MultipleEnumSetting<Target>): Boolean {
    val targets = targetSetting.value
    return when {
      targets.contains(Target.PLAYER) && livingEntity is PlayerEntity -> true
      targets.contains(Target.MOB) && livingEntity is MobEntity -> true
      targets.contains(Target.ANIMAL) && livingEntity is AnimalEntity -> true
      targets.contains(Target.VILLAGER) && livingEntity is VillagerEntity -> true
      else -> false
    }
  }
}
