package dev.rabies.vox.events.packet;

import dev.rabies.vox.events.CancellableEvent;
import dev.rabies.vox.events.EventTiming;
import net.minecraft.network.Packet;

public class PacketEvent extends CancellableEvent {

    public final Packet<?> packet;

    public PacketEvent(EventTiming timing, Packet<?> packet) {
        super(timing);
        this.packet = packet;
    }
}
