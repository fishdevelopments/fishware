package de.fishdevelopments.fishware.mixin;

import de.fishdevelopments.fishware.Fishware;
import de.fishdevelopments.fishware.event.impl.Render3DEvent;
import net.minecraft.client.render.*;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
  @Inject(method = "render", at = @At("HEAD"))
  public void post_render3devent_pre(
      RenderTickCounter tickCounter,
      boolean renderBlockOutline,
      Camera camera,
      GameRenderer gameRenderer,
      LightmapTextureManager lightmapTextureManager,
      Matrix4f matrix4f,
      Matrix4f matrix4f2,
      CallbackInfo ci) {
    Fishware.Companion.getINSTANCE()
        .getEventBus()
        .post(new Render3DEvent(matrix4f, matrix4f2, Render3DEvent.State.PRE));
  }

  @Inject(method = "render", at = @At("HEAD"))
  public void post_render3devent_post(
      RenderTickCounter tickCounter,
      boolean renderBlockOutline,
      Camera camera,
      GameRenderer gameRenderer,
      LightmapTextureManager lightmapTextureManager,
      Matrix4f matrix4f,
      Matrix4f matrix4f2,
      CallbackInfo ci) {
    Fishware.Companion.getINSTANCE()
        .getEventBus()
        .post(new Render3DEvent(matrix4f, matrix4f2, Render3DEvent.State.POST));
  }
}
