package dev.rabies.vox.properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BoolProperty extends BaseProperty<Boolean> {

    public BoolProperty(String id, String name, Boolean defaultValue) {
        super(id, name, defaultValue);
    }

    @Override
    public void deserialize(JsonElement element) {
        if (!element.isJsonPrimitive()) return;
        set(element.getAsBoolean());
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(get());
    }
}
