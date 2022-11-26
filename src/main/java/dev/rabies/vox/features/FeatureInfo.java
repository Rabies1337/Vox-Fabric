package dev.rabies.vox.features;

import org.lwjgl.glfw.GLFW;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureInfo {
    String name();

    Category category();

    int bindCode() default GLFW.GLFW_KEY_UNKNOWN;
}
