package dev.rabies.vox.mixins;

import dev.rabies.vox.events.EventHandlers;
import dev.rabies.vox.events.game.InputKeyEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(method = "onKey", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/util/InputUtil;isKeyPressed(JI)Z",
            ordinal = 5
    ), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        InputKeyEvent inputKeyEvent = new InputKeyEvent(key, action);
        EventHandlers.dispatchEvent(inputKeyEvent);

        if (inputKeyEvent.isCancelled()) {
            ci.cancel();
        }
    }
}
