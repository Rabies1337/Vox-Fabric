package dev.rabies.vox.features;

import com.google.gson.JsonElement;
import dev.rabies.vox.Globals;
import dev.rabies.vox.VoxMod;
import dev.rabies.vox.events.EventHandlers;
import dev.rabies.vox.properties.Property;

import java.util.LinkedList;
import java.util.List;

// The name
public class BaseFeature implements Feature, Globals {

    protected final List<Property<?>> properties = new LinkedList<>();

    protected final String name;
    protected final Category category;

    protected boolean toggled;
    protected String suffix;
    protected int bindCode;

    public BaseFeature() {
        FeatureInfo info = this.getClass().getAnnotation(FeatureInfo.class);
        name = info.name();
        category = info.category();
        bindCode = info.bindCode();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public int getBindCode() {
        return bindCode;
    }

    @Override
    public void setBindCode(int bindCode) {
        this.bindCode = bindCode;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }

    @Override
    public <T extends Property<?>> T add(T property) {
        properties.add(property);
        return property;
    }

    @Override
    public List<Property<?>> getProperties() {
        return properties;
    }

    @Override
    public void toggle() {
        toggled = !toggled;

        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }

        VoxMod.getLogger().debug("Toggled " + name);
    }

    @Override
    public void onEnable() {
        EventHandlers.register(this);
    }

    @Override
    public void onDisable() {
        EventHandlers.unregister(this);
    }

    @Override
    public void deserialize(JsonElement element) {
    }

    @Override
    public JsonElement serialize() {
        return null;
    }
}
