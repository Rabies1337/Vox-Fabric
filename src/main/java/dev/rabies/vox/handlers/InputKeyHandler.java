package dev.rabies.vox.handlers;

import dev.rabies.vox.VoxMod;
import dev.rabies.vox.events.EventHandler;
import dev.rabies.vox.events.EventTiming;
import dev.rabies.vox.events.game.InputKeyEvent;
import org.lwjgl.glfw.GLFW;

public class InputKeyHandler {
    @EventHandler(timing = EventTiming.PRE)
    public void onInputKey(InputKeyEvent event) {
        if (event.key == GLFW.GLFW_KEY_UNKNOWN) {
            return;
        }

        VoxMod.getInstance().getFeatures().forEach(feature -> {
            if (feature.getBindCode() == event.key) {
                feature.toggle();
            }
        });
    }
}
