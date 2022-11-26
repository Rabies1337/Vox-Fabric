package dev.rabies.vox.properties;

public abstract class BaseProperty<T> implements Property<T> {

    protected final String id, name;
    protected final T defaultValue;
    protected T value;

    public BaseProperty(String id, String name, T defaultValue) {
        this.id = id;
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public void reset() {
        value = defaultValue;
    }
}
