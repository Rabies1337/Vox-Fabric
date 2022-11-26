package dev.rabies.vox.features;

import dev.rabies.vox.properties.Property;
import dev.rabies.vox.utils.Serializable;

import java.util.List;

public interface Feature extends Serializable {
    String getName();

    Category getCategory();

    int getBindCode();

    void setBindCode(int code);

    String getSuffix();

    boolean isToggled();

    <T extends Property<?>> T add(T property);

    List<Property<?>> getProperties();

    void toggle();

    void onEnable();

    void onDisable();
}
