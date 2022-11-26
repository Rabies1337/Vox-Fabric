package dev.rabies.vox.events;

public interface EventAdapter<T extends Event> {
    void call(T event);

    EventPriority getPriority();

    boolean isIgnoreCancelled();

    Class<T> getEventClass();
}
