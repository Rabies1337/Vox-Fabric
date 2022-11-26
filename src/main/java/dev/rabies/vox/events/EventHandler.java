package dev.rabies.vox.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    EventPriority priority() default EventPriority.NORMAL;

    boolean ignoreCancelled() default true;

    EventTiming[] timing();
}
