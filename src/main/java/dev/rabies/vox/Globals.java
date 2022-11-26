package dev.rabies.vox;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public interface Globals {

    MinecraftClient mc = MinecraftClient.getInstance();

    default boolean isNull() {
        return mc.world == null || mc.player == null;
    }

    default boolean isMoving() {
        if (isNull()) return false;
        return mc.player.forwardSpeed > 0 || mc.player.sidewaysSpeed > 0;
    }

    default void setMotion(double scale) {
        if (isNull()) {
            return;
        }

        double forward = mc.player.forwardSpeed;
        double strafe = mc.player.sidewaysSpeed;
        if (forward == 0.0D && strafe == 0.0D) {
            mc.player.setVelocity(0, mc.player.getVelocity().y, 0);
        } else {
            Vec3d velocity = new Vec3d(-forward, mc.player.getVelocity().y, strafe).normalize().rotateY((float) Math.toRadians(90 - mc.player.headYaw)).multiply(scale);
            mc.player.setVelocity(velocity);
        }
    }
}
