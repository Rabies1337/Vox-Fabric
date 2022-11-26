package dev.rabies.vox.events;

public interface Event {
    EventTiming getTiming();

    boolean isAsync();

    boolean isPre();

    boolean isPost();
}
