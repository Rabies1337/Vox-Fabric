package dev.rabies.vox.mixins;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class MixinCamera {

    @Shadow
    private Entity focusedEntity;

    @Shadow
    private float cameraY;

    @Shadow
    private float lastCameraY;

    @Inject(method = "updateEyeHeight", at = @At("HEAD"), cancellable = true)
    public void updateEyeHeight(CallbackInfo ci) {
        if (focusedEntity != null) {
            lastCameraY = cameraY;
            cameraY = focusedEntity.getStandingEyeHeight();
            ci.cancel();
        }
    }
}
