package dev.rabies.vox.events.packet;

import dev.rabies.vox.events.EventTiming;
import net.minecraft.network.Packet;

public class PacketReceiveEvent extends PacketEvent {
    public PacketReceiveEvent(EventTiming timing, Packet<?> packet) {
        super(timing, packet);
    }
}
