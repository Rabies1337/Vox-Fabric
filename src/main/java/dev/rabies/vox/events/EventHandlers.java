package dev.rabies.vox.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class EventHandlers {

    private static final Set<EventMap> eventMaps = new CopyOnWriteArraySet<>();

    @SuppressWarnings("unchecked")
    public static <T extends Event> void dispatchEvent(T event) {
        LinkedList<EventAdapter<T>> adapters = new LinkedList<>();
        for (EventMap map : getAllEventMap(event.getClass())) {
            for (EventAdapter<?> adapter : map.adapters) {
                adapters.add((EventAdapter<T>) adapter);
            }
        }

        adapters.sort(Comparator.comparing(EventAdapter::getPriority));
        for (EventAdapter<T> adapter : adapters) {
            if (event instanceof Cancellable && ((Cancellable) event).isCancelled() && adapter.isIgnoreCancelled()) {
                continue;
            }

            adapter.call(event);
        }
    }

    public static void register(Object obj, EventAdapter<?> adapter) {
        EventMap map = getEventMap(adapter.getEventClass());
        if (map == null) {
            map = new EventMap(obj, adapter.getEventClass(), new ArrayList<>());
            eventMaps.add(map);
        }
        map.adapters.add(adapter);
        map.adapters.sort(Comparator.comparing(EventAdapter::getPriority));
    }

    public static void register(Object o) {
        for (Method method : o.getClass().getMethods()) {
            try {
                register(o, new MethodEventAdapter(o, method));
            } catch (IllegalArgumentException ignore) {
                // skip
            }
        }
    }

    public static void unregister(Object o) {
        eventMaps.removeIf(map -> o.equals(map.obj));
    }

    private static EventMap getEventMap(Class<?> eventClass) {
        for (EventMap map : eventMaps) {
            if (eventClass.equals(map.getClass())) {
                return map;
            }
        }
        return null;
    }

    private static HashSet<EventMap> getAllEventMap(Class<?> eventClass) {
        HashSet<EventMap> result = new HashSet<>();
        for (EventMap map : eventMaps) {
            if (map.eventClass.isAssignableFrom(eventClass)) {
                result.add(map);
            }
        }
        return result;
    }

    private record EventMap(Object obj, Class<?> eventClass, List<EventAdapter<?>> adapters) {
        private EventMap(Object obj, Class<?> eventClass, List<EventAdapter<?>> adapters) {
            this.obj = obj;
            this.eventClass = eventClass;
            this.adapters = new CopyOnWriteArrayList<>(adapters);
        }
    }
}
