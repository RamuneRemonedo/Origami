package tokyo.ramune.origami.service;

import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.origami.system.Command;
import tokyo.ramune.origami.system.Listener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.logging.Logger;

@ServiceHandler(priority = ServicePriority.NORMAL)
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

    public void onReload() {
    }

    public void onStart() {
    }

    public void onStop() {
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
