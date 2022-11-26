package dev.rabies.vox.utils;

import com.google.gson.JsonElement;

public interface Serializable {
    void deserialize(JsonElement element);

    JsonElement serialize();
}
