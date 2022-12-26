package tokyo.ramune.origami.system.service;

import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.origami.system.command.Command;
import tokyo.ramune.origami.system.listener.Listener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.logging.Logger;

public class Service {
    private final JavaPlugin plugin;
    private final Logger logger;
    private final boolean isEnabled = false;

    public Service(@Nonnull JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void onLoad() {
    }

    public void onUnload() {
    }

    @Nullable
    public Set<Listener<? extends Event>> getListeners() {
        return null;
    }

    @Nullable
    public Set<Command> getCommands() {
        return null;
    }

    @Nullable
    public Set<Permission> getPermissions() {
        return null;
    }
}
