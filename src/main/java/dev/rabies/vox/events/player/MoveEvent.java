package dev.rabies.vox.events.player;

import dev.rabies.vox.events.CancellableEvent;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class MoveEvent extends CancellableEvent {

    public final MovementType moveType;
    public final Vec3d move;

    public MoveEvent(MovementType moveType, Vec3d move) {
        this.moveType = moveType;
        this.move = move;
    }
}
