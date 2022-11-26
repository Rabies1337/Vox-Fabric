package dev.rabies.vox.properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class DoubleProperty extends BaseProperty<Double> {

    protected final double min, max;
    protected final double interval;

    public DoubleProperty(String id, String name, double defaultValue, double min, double max, double interval) {
        super(id, name, defaultValue);
        this.min = min;
        this.max = max;
        this.interval = interval;
    }

    @Override
    public void deserialize(JsonElement element) {
        if (!element.isJsonPrimitive()) return;
        set(element.getAsDouble());
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(get());
    }

    public static class Builder {
        private double defaultValue = 0.0;
        private String id = "", name = "";
        private double min = 0.0, max = 10.0;
        private double interval = 1.0;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDefaultValue(double defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder setMin(double min) {
            this.min = min;
            return this;
        }

        public Builder setMax(double max) {
            this.max = max;
            return this;
        }

        public Builder setInterval(double interval) {
            this.interval = interval;
            return this;
        }

        public DoubleProperty build() {
            return new DoubleProperty(id, name, defaultValue, min, max, interval);
        }
    }
}
