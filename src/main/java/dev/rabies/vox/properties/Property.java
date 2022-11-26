package dev.rabies.vox.properties;

import dev.rabies.vox.utils.Serializable;

public interface Property<T> extends Serializable {
    String name();

    T get();

    void set(T value);

    void reset();
}
