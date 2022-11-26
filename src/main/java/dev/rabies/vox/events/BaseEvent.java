package dev.rabies.vox.events;

import net.minecraft.client.MinecraftClient;

public class BaseEvent implements Event {

    private final EventTiming timing;
    private final boolean async;

    public BaseEvent(EventTiming timing, boolean async) {
        this.timing = timing;
        this.async = async;
    }

    public BaseEvent(EventTiming timing) {
        this(timing, !MinecraftClient.getInstance().isOnThread());
    }

    @Override
    public EventTiming getTiming() {
        return timing;
    }

    @Override
    public boolean isAsync() {
        return async;
    }

    @Override
    public boolean isPre() {
        return timing == EventTiming.PRE;
    }

    @Override
    public boolean isPost() {
        return timing == EventTiming.POST;
    }
}
