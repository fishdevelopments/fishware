package de.fishdevelopments.fishware.mixin;

import de.fishdevelopments.fishware.Fishware;
import de.fishdevelopments.fishware.event.impl.Render2DEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

  @Inject(method = "render", at = @At("HEAD"))
  public void post_render2devent(
      DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
    Fishware.Companion.getINSTANCE().getEventBus().post(new Render2DEvent(context));
  }
}
