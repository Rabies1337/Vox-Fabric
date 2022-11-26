package dev.rabies.vox.events;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancelled);
}
