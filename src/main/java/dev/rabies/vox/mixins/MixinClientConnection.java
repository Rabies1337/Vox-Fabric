package dev.rabies.vox.mixins;

import dev.rabies.vox.VoxMod;
import dev.rabies.vox.events.EventHandlers;
import dev.rabies.vox.events.EventTiming;
import dev.rabies.vox.events.packet.PacketReceiveEvent;
import dev.rabies.vox.events.packet.PacketSendEvent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {

    @Shadow
    private Channel channel;

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    protected void channelRead0(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
        if (!channel.isOpen()) {
            return;
        }

        PacketReceiveEvent packetReceiveEvent = new PacketReceiveEvent(EventTiming.PRE, packet);
        EventHandlers.dispatchEvent(packetReceiveEvent);

        if (packetReceiveEvent.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "sendInternal", at = @At("HEAD"), cancellable = true)
    private void sendInternal(Packet<?> packet, PacketCallbacks callbacks, NetworkState packetState, NetworkState currentState, CallbackInfo ci) {
        if (!channel.isOpen() || VoxMod.getInstance().ignorePacket) {
            VoxMod.getInstance().ignorePacket = false;
            return;
        }

        PacketSendEvent packetSendEvent = new PacketSendEvent(EventTiming.PRE, packet);
        EventHandlers.dispatchEvent(packetSendEvent);

        if (packetSendEvent.isCancelled()) {
            ci.cancel();
        }
    }
}
