package de.fishdevelopments.fishware.util.nanovg

import de.fishdevelopments.fishware.Fishware.Companion.MC
import org.lwjgl.opengl.GL11C
import org.nvgu.NVGU

object NanoVGRenderer {
  val NVGU: NVGU =
    NVGU()
      .create()
      .createFont(
        Font.GREYCLIFF_CF_DEMIBOLD.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream(
          "/assets/fishware/fonts/GreycliffCF-DemiBold.ttf"
        ),
      )
      .createFont(
        Font.GREYCLIFF_CF_HEAVY.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream("/assets/fishware/fonts/GreycliffCF-Heavy.ttf"),
      )
      .createFont(
        Font.MINECRAFT.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream("/assets/fishware/fonts/Minecraft.ttf"),
      )
      .createFont(
        Font.PROGGYCLEAN.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream("/assets/fishware/fonts/ProggyClean.ttf"),
      )
      .createFont(
        Font.UBUNTUMONO_BOLD.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream("/assets/fishware/fonts/UbuntuMono-Bold.ttf"),
      )
      .createFont(
        Font.UBUNTUMONO_REGULAR.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream(
          "/assets/fishware/fonts/UbuntuMono-Regular.ttf"
        ),
      )
      .createFont(
        Font.VOLTE_BOLD.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream("/assets/fishware/fonts/Volte-Bold.ttf"),
      )
      .createFont(
        Font.VOLTE_MEDIUM.identifier,
        NanoVGRenderer.javaClass.getResourceAsStream("/assets/fishware/fonts/Volte-Medium.ttf"),
      )

  fun render(runnable: Runnable) {
    NVGU.beginFrame(MC.window.width, MC.window.height)
    runnable.run()
    NVGU.endFrame()

    GL11C.glBlendFunc(GL11C.GL_SRC_ALPHA, GL11C.GL_ONE_MINUS_SRC_ALPHA)
    GL11C.glEnable(GL11C.GL_CULL_FACE)
    GL11C.glCullFace(GL11C.GL_BACK)
    GL11C.glFrontFace(GL11C.GL_CCW)
    GL11C.glDisable(GL11C.GL_BLEND)
    GL11C.glDisable(GL11C.GL_DEPTH_TEST)
    GL11C.glDisable(GL11C.GL_SCISSOR_TEST)
    GL11C.glColorMask(true, true, true, true)
    GL11C.glStencilMask(-0x1)
    GL11C.glStencilOp(GL11C.GL_KEEP, GL11C.GL_KEEP, GL11C.GL_KEEP)
    GL11C.glStencilFunc(GL11C.GL_ALWAYS, 0, -0x1)
  }
}
