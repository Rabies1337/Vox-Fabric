package dev.rabies.vox.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Shadow
    private int fpsCounter;

    @Inject(method = "render", at = @At("HEAD"))
    public void onRenderStart(CallbackInfo ci) {
        // Fake Fps :wt:
        fpsCounter++;
    }
}
