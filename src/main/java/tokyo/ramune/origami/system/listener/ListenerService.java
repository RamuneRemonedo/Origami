package tokyo.ramune.origami.system.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.origami.system.service.Service;

import javax.annotation.Nonnull;

public class ListenerService extends Service {
    public ListenerService(JavaPlugin plugin) {
        super(plugin);
    }

    @SafeVarargs
    public final void registerEvent(Listener<? extends Event>... listeners) {
        for (Listener<? extends Event> listener : listeners) {
            registerEvent(listeners);
        }
    }

    public void registerEvent(Listener<? extends Event> listener) {
        try {
            Bukkit.getPluginManager().registerEvent(listener.getEventClass(), listener, listener.getEventPriority(), EventExecutor.create(listener.getClass().getMethod("onEvent", Event.class), Event.class), getPlugin());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SafeVarargs
    public final void unregisterEvent(@Nonnull Listener<? extends Event>... listeners) {
        for (Listener<? extends Event> listener : listeners) {
            unregisterEvent(listener);
        }
    }

    public void unregisterEvent(@Nonnull Listener<? extends Event> listener) {
        for (RegisteredListener registeredListener : HandlerList.getRegisteredListeners(getPlugin())) {
            if (registeredListener.equals(listener))
                HandlerList.unregisterAll((org.bukkit.event.Listener) registeredListener);
        }
    }
}
