package dev.nymnux.simplepaperdoll.mixin;

import dev.nymnux.simplepaperdoll.SimplePaperDollMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("HEAD"))
    private void rhizome$onHudRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (this.client.currentScreen != null && this.client.currentScreen instanceof DownloadingTerrainScreen) return;
        if (this.client.options.hudHidden) return;
        SimplePaperDollMod.SIMPLE_PAPER_DOLL.onHudRender(context, tickCounter.getTickProgress(false));
    }

}
