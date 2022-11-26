package dev.rabies.vox.events.player;

import dev.rabies.vox.events.CancellableEvent;
import dev.rabies.vox.events.EventTiming;

public class UpdatePositionEvent extends CancellableEvent {

    public UpdatePositionEvent(EventTiming timing) {
        super(timing);
    }
}
