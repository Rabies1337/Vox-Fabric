package dev.rabies.vox.handlers;

import dev.rabies.vox.VoxMod;
import dev.rabies.vox.commands.Command;
import dev.rabies.vox.events.EventHandler;
import dev.rabies.vox.events.EventTiming;
import dev.rabies.vox.events.packet.PacketSendEvent;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class SendChatHandler {

    @EventHandler(timing = EventTiming.PRE)
    public void onSendPacket(PacketSendEvent event) {
        if (event.packet instanceof ChatMessageC2SPacket chatPacket) {
            if (chatPacket.chatMessage().startsWith(":")) {
                event.setCancelled(true);

                String[] args = chatPacket.chatMessage().substring(1).split(" ");
                Command command = VoxMod.getInstance().getCommandByAlias(args[0]);
                if (command == null) {
                    return;
                }

                command.execute(args);
            }
        }
    }
}
