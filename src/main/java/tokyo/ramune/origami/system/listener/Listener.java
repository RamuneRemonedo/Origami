package tokyo.ramune.origami.system.listener;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class Listener<E extends Event> implements org.bukkit.event.Listener {
    private final Class<E> eventClass;
    private final Consumer<E> eventConsumer;
    private final EventPriority eventPriority;
    private final boolean isIgnoredCancelled;

    public Listener(Class<E> eventClass, @Nonnull Consumer<E> eventConsumer, @Nonnull EventPriority eventPriority, boolean isIgnoredCancelled) {
        this.eventClass = eventClass;
        this.eventConsumer = eventConsumer;
        this.eventPriority = eventPriority;
        this.isIgnoredCancelled = isIgnoredCancelled;
    }

    @EventHandler
    public void onEvent(E event) {
        eventConsumer.accept(event);
    }

    public Consumer<E> getEventConsumer() {
        return eventConsumer;
    }

    public Class<E> getEventClass() {
        return eventClass;
    }

    public EventPriority getEventPriority() {
        return eventPriority;
    }

    public boolean isIgnoredCancelled() {
        return isIgnoredCancelled;
    }
}
