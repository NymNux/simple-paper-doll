package dev.nymnux.simplepaperdoll.mixin;

import dev.nymnux.simplepaperdoll.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Inject(method = "close", at = @At("HEAD"))
    private void simplepaperdoll$saveConfig(CallbackInfo ci) {
        ModConfig.save();
    }

}
