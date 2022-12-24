package tokyo.ramune.origami.system;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.origami.service.Service;
import tokyo.ramune.origami.service.ServiceHandler;
import tokyo.ramune.origami.service.ServicePriority;

import javax.annotation.Nonnull;

@ServiceHandler(priority = ServicePriority.SYSTEM)
public class ListenerService extends Service {
    public ListenerService(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onStart() {
        super.onStart();
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
            if (((org.bukkit.event.Listener) registeredListener).equals(listener))
                HandlerList.unregisterAll((org.bukkit.event.Listener) registeredListener);
        }
    }
}
