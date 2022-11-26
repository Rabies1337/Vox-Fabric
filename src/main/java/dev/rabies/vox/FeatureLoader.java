package dev.rabies.vox;

import dev.rabies.vox.features.BaseFeature;
import dev.rabies.vox.features.Feature;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FeatureLoader {

    protected final List<Feature> features = new ArrayList<>();

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
        // TODO: 2022/11/26
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
