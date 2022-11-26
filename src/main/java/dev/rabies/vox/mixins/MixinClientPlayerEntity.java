package dev.rabies.vox.mixins;

import dev.rabies.vox.events.EventHandlers;
import dev.rabies.vox.events.EventTiming;
import dev.rabies.vox.events.player.MoveEvent;
import dev.rabies.vox.events.player.UpdatePositionEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Inject(method = "sendMovementPackets", at = @At("HEAD"), cancellable = true)
    private void onStartUpdatePosition(CallbackInfo ci) {
        UpdatePositionEvent updatePositionEvent = new UpdatePositionEvent(EventTiming.PRE);
        EventHandlers.dispatchEvent(updatePositionEvent);

        if (updatePositionEvent.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "sendMovementPackets", at = @At("RETURN"))
    private void onEndUpdatePosition(CallbackInfo ci) {
        UpdatePositionEvent updatePositionEvent = new UpdatePositionEvent(EventTiming.POST);
        EventHandlers.dispatchEvent(updatePositionEvent);
    }

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void move(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        MoveEvent moveEvent = new MoveEvent(movementType, movement);
        EventHandlers.dispatchEvent(moveEvent);

        if (moveEvent.isCancelled()) {
            ci.cancel();
        }
    }
}
