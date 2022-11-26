package dev.rabies.vox.events.game;

import dev.rabies.vox.events.CancellableEvent;

public class InputKeyEvent extends CancellableEvent {

    public final int key, action;

    public InputKeyEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }
}
