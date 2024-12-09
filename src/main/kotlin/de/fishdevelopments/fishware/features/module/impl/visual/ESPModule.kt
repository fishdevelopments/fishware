package de.fishdevelopments.fishware.features.module.impl.visual

import de.fishdevelopments.fishware.Fishware.Companion.MC
import de.fishdevelopments.fishware.event.impl.Render2DEvent
import de.fishdevelopments.fishware.event.impl.Render3DEvent
import de.fishdevelopments.fishware.features.module.Module
import de.fishdevelopments.fishware.features.module.ModuleCategory
import de.fishdevelopments.fishware.system.setting.impl.BooleanSetting
import de.fishdevelopments.fishware.system.setting.impl.ColorSetting
import de.fishdevelopments.fishware.system.setting.impl.EnumSetting
import de.fishdevelopments.fishware.system.setting.impl.NumberSetting
import de.fishdevelopments.fishware.util.RenderUtil
import de.fishdevelopments.fishware.util.nanovg.Font
import de.fishdevelopments.fishware.util.nanovg.NanoVGRenderer
import java.awt.Color
import me.zero.alpine.listener.Subscribe
import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec2f
import net.minecraft.util.math.Vec3d
import org.nvgu.util.Alignment
import org.nvgu.util.Border

class ESPModule : Module(ModuleCategory.VISUAL, "ESP", "Makes entities visible behind walls") {

  private val ddSetting = BooleanSetting("2D", false)

  private val boxSetting = BooleanSetting("Box", true) { this.ddSetting.value }

  private val boxRadiusSetting =
    NumberSetting("Radius", 2, { this.boxSetting.value && this.boxSetting.isVisible() }, 0, 10)

  private val boxThicknessSetting =
    NumberSetting("Thickness", 2, { this.boxSetting.value && this.boxSetting.isVisible() }, 1, 10)

  private val boxColorSetting =
    ColorSetting("Color", Color.CYAN) { this.boxSetting.value && this.boxSetting.isVisible() }

  private val boxBorderMode =
    EnumSetting("Border mode", Border.MIDDLE) { boxSetting.value && boxSetting.isVisible() }

  private val nametagsSetting = BooleanSetting("Nametags", false) { this.ddSetting.value }

  private val nametagsColorSetting =
    ColorSetting("Font color", Color.white) {
      this.nametagsSetting.value && this.nametagsSetting.isVisible()
    }

  private val nametagsFontSizeSetting =
    NumberSetting(
      "Font size",
      16,
      { this.nametagsSetting.value && this.nametagsSetting.isVisible() },
      12,
      28,
    )

  private val nametagsFontSetting =
    EnumSetting("Font", Font.entries.first()) {
      this.nametagsSetting.value && this.nametagsSetting.isVisible()
    }

  init {
    this.settings.add(this.ddSetting)
    this.settings.add(this.boxSetting)
    this.settings.add(this.boxRadiusSetting)
    this.settings.add(this.boxThicknessSetting)
    this.settings.add(this.boxColorSetting)
    this.settings.add(this.boxBorderMode)
    this.settings.add(this.nametagsSetting)
    this.settings.add(this.nametagsColorSetting)
    this.settings.add(this.nametagsFontSizeSetting)
    this.settings.add(this.nametagsFontSetting)
  }

  private val hashMap = HashMap<Entity, Pair<Rectangle, Boolean>>()

  @Subscribe
  fun onRender3DEvent(render3DEvent: Render3DEvent) {
    if (render3DEvent.state != Render3DEvent.State.POST) {
      return
    }

    this.hashMap.clear()

    val world = MC.world ?: return
    val player = MC.player ?: return

    for (entity in world.entities) {

      if (entity == player) {
        continue
      }

      val prevPos = Vec3d(entity.lastRenderX, entity.lastRenderY, entity.lastRenderZ)
      val interp =
        prevPos.add(
          (entity.pos.subtract(prevPos)).multiply(
            MC.renderTickCounter.getTickDelta(false).toDouble()
          )
        )
      val boundingBox = entity.boundingBox.offset(interp.subtract(entity.pos))

      val corners =
        arrayOf(
          Vec3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ),
          Vec3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ),
          Vec3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ),
          Vec3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ),
          Vec3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ),
          Vec3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ),
          Vec3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ),
          Vec3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ),
        )

      var rectangle: Rectangle? = null

      var visible = false

      for (corner in corners) {
        val projection =
          RenderUtil.project(render3DEvent.positionMatrix, render3DEvent.projectionMatrix, corner)

        if (projection.second) {
          visible = true
        }

        val projected = projection.first

        if (rectangle == null)
          rectangle = Rectangle(projected.x, projected.y, projected.x, projected.y)
        else {
          if (rectangle.x > projected.x) {
            rectangle.x = projected.x
          }

          if (rectangle.y > projected.y) {
            rectangle.y = projected.y
          }

          if (rectangle.z < projected.x) {
            rectangle.z = projected.x
          }

          if (rectangle.w < projected.y) {
            rectangle.w = projected.y
          }
        }
      }

      this.hashMap[entity] = rectangle!! to visible
    }
  }

  @Subscribe
  fun onRender2DEvent(render2DEvent: Render2DEvent) {
    for ((entity, pair) in this.hashMap.entries) {
      if (pair.second) {
        val rectangle = pair.first

        NanoVGRenderer.render {
          if (this.boxSetting.value) {
            NanoVGRenderer.NVGU.roundedRectangleBorder(
              rectangle.x.toFloat() * MC.window.scaleFactor.toFloat(),
              rectangle.y.toFloat() * MC.window.scaleFactor.toFloat(),
              (rectangle.z - rectangle.x).toFloat() * MC.window.scaleFactor.toFloat(),
              (rectangle.w - rectangle.y).toFloat() * MC.window.scaleFactor.toFloat(),
              this.boxRadiusSetting.value.toFloat(),
              this.boxThicknessSetting.value.toFloat(),
              this.boxColorSetting.value,
              this.boxBorderMode.value,
            )
          }

          if (this.nametagsSetting.value) {
            NanoVGRenderer.NVGU.text(
              entity.displayName!!.string,
              (rectangle.x.toFloat() + (rectangle.z - rectangle.x).toFloat() / 2f) *
                MC.window.scaleFactor.toFloat(),
              rectangle.y.toFloat() * MC.window.scaleFactor.toFloat(),
              this.nametagsColorSetting.value,
              this.nametagsFontSetting.value.identifier,
              this.nametagsFontSizeSetting.value,
              Alignment.CENTER_BOTTOM,
            )
          }
        }
      }
    }
  }

  inner class Rectangle(var x: Double, var y: Double, var z: Double, var w: Double) {
    fun center() = Vec2f((x + z).toFloat() / 2F, (y + w).toFloat() / 2F)
  }
}
