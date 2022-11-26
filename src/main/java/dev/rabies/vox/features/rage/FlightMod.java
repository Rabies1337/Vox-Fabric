package dev.rabies.vox.features.rage;

import dev.rabies.vox.events.EventHandler;
import dev.rabies.vox.events.EventTiming;
import dev.rabies.vox.events.player.UpdatePositionEvent;
import dev.rabies.vox.features.BaseFeature;
import dev.rabies.vox.features.Category;
import dev.rabies.vox.features.FeatureInfo;
import dev.rabies.vox.properties.DoubleProperty;
import org.lwjgl.glfw.GLFW;

@FeatureInfo(name = "Flight", category = Category.RAGE, bindCode = GLFW.GLFW_KEY_F)
public class FlightMod extends BaseFeature {

    private final DoubleProperty speedProperty = add(new DoubleProperty.Builder()
            .setId("flight-speed")
            .setName("Speed")
            .setMin(0.1)
            .setMax(5.0)
            .setInterval(0.1)
            .setDefaultValue(1.0)
            .build()
    );

    @Override
    public void onDisable() {
        super.onDisable();
        setMotion(0.0);
    }

    @EventHandler(timing = EventTiming.PRE)
    public void onUpdatePosition(UpdatePositionEvent event) {
        mc.player.setVelocity(0, 0, 0);

        double speed = mc.options.sprintKey.isPressed() ? speedProperty.get() * 1.5 : speedProperty.get();
        if (mc.options.jumpKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().x, speed / 1.8, mc.player.getVelocity().z);
        } else if (mc.options.sneakKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().x, -speed / 1.8, mc.player.getVelocity().z);
        }

        if (isMoving()) {
            setMotion(speed);
        }
    }
}
