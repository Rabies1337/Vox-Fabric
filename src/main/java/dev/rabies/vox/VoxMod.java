package dev.rabies.vox;

import dev.rabies.vox.events.EventHandlers;
import dev.rabies.vox.handlers.InputKeyHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoxMod extends FeatureLoader implements ModInitializer {

    private static final Logger logger = LoggerFactory.getLogger("Vox");
    private static VoxMod instance;

    public boolean ignorePacket;

    @Override
    public void onInitialize() {
        // Instance
        instance = this;

        // Load Features
        loadFeatures();
        loadCommands();

        // Register handlers
        EventHandlers.register(new InputKeyHandler());
    }

    public static VoxMod getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return logger;
    }
}
