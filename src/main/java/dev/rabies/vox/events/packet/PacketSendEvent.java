package dev.rabies.vox.events.packet;

import dev.rabies.vox.events.EventTiming;
import net.minecraft.network.Packet;

public class PacketSendEvent extends PacketEvent {
    public PacketSendEvent(EventTiming timing, Packet<?> packet) {
        super(timing, packet);
    }
}
