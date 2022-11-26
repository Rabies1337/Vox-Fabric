package dev.rabies.vox.features.legit;

import dev.rabies.vox.events.EventHandler;
import dev.rabies.vox.events.EventTiming;
import dev.rabies.vox.events.player.MoveEvent;
import dev.rabies.vox.features.BaseFeature;
import dev.rabies.vox.features.Category;
import dev.rabies.vox.features.FeatureInfo;
import net.minecraft.entity.MovementType;

@FeatureInfo(name = "AutoSprint", category = Category.LEGIT)
public class AutoSprintMod extends BaseFeature {

    @EventHandler(timing = EventTiming.PRE)
    public void onMove(MoveEvent event) {
        if (event.moveType == MovementType.SELF && isMoving() && mc.options.forwardKey.isPressed()) {
            mc.player.setSprinting(true);
        }
    }
}
