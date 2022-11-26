package dev.rabies.vox;

import dev.rabies.vox.commands.BaseCommand;
import dev.rabies.vox.commands.Command;
import dev.rabies.vox.features.BaseFeature;
import dev.rabies.vox.features.Feature;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FeatureLoader {

    protected final List<Feature> features = new ArrayList<>();
    protected final List<Command> commands = new ArrayList<>();

    protected void loadFeatures() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("dev.rabies.vox"));
            Set<Class<? extends BaseFeature>> classes = reflections.getSubTypesOf(BaseFeature.class);
            for (Class<? extends BaseFeature> clazz : classes) {
                features.add(clazz.newInstance());
            }
        } catch (Exception e) {
            VoxMod.getLogger().warn(e.getMessage());
        }
    }

    protected void loadCommands() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("dev.rabies.vox"));
            Set<Class<? extends BaseCommand>> classes = reflections.getSubTypesOf(BaseCommand.class);
            for (Class<? extends BaseCommand> clazz : classes) {
                commands.add(clazz.newInstance());
            }
        } catch (Exception e) {
            VoxMod.getLogger().warn(e.getMessage());
        }
    }

    public Command getCommandByAlias(String alias) {
        for (Command command : commands) {
            for (String als : command.getAliases()) {
                if (als.equalsIgnoreCase(alias)) {
                    return command;
                }
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name) {
        for (Feature feature : features) {
            if (feature.getName().equalsIgnoreCase(name)) {
                return feature;
            }
        }
        return null;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
