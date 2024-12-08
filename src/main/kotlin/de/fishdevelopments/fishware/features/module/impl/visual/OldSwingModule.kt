package de.fishdevelopments.fishware.features.module.impl.visual

import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.ModuleCategory
import net.minecraft.item.ItemStack

class OldSwingModule :
    Module(ModuleCategory.VISUAL, "Old swing", "Hides the equip progress animation.") {

  fun canEquipBeIgnored(oldItem: ItemStack?, newItem: ItemStack?): Boolean {
    if (oldItem == null || newItem == null) {
      return false
    }

    if (oldItem.isEmpty && newItem.isEmpty) {
      return true
    }

    if (oldItem.isEmpty || newItem.isEmpty) {
      return false
    }

    return oldItem.isOf(newItem.item)
  }
}
