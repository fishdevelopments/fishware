package de.fishdevelopments.fishware.util.render

import de.fishdevelopments.fishware.Fishware.Companion.MC
import net.minecraft.util.math.Vec3d
import org.joml.Matrix4f
import org.joml.Vector4f

object RenderUtil {

  fun project(modelView: Matrix4f, projection: Matrix4f, vector: Vec3d): Pair<Vec3d, Boolean> {
    val camPos = vector.subtract(MC.gameRenderer.camera.pos)
    val vec1 =
      Vector4f(camPos.x.toFloat(), camPos.y.toFloat(), camPos.z.toFloat(), 1F).mul(modelView)
    val screenPos = vec1.mul(projection)

    val newW = 1.0 / screenPos.w * 0.5

    var position =
      Vec3d(screenPos.x * newW + 0.5, screenPos.y * newW + 0.5, screenPos.z * newW + 0.5)

    position =
      Vec3d(
        position.x * MC.window.scaledWidth.toDouble(),
        (1.0 - position.y) * MC.window.scaledHeight.toDouble(),
        position.z,
      )

    if (screenPos.w <= 0.0) {
      position =
        Vec3d(
          MC.window.scaledWidth - position.x,
          MC.window.scaledHeight - position.y,
          screenPos.z.toDouble(),
        )
    }

    return position to (screenPos.w > 0.0)
  }
}
