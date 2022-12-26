package tokyo.ramune.origami.system.service;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.origami.system.command.Command;
import tokyo.ramune.origami.system.command.CommandService;
import tokyo.ramune.origami.system.listener.ListenerService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class ManagerService extends Service {
    private final Set<Service> services;
    private ListenerService listenerService;
    private CommandService commandService;

    public ManagerService(@Nonnull JavaPlugin plugin) {
        super(plugin);
        services = new HashSet<>();

        loadSystemServices();
    }

    public void loadSystemServices() {
        listenerService = new ListenerService(getPlugin());
        commandService = new CommandService(getPlugin());
    }

    public void unloadSystemServices() {
        listenerService.onUnload();
        listenerService = null;

        commandService.onUnload();
        commandService = null;
    }

    public Service loadService(@Nonnull Service service) {
        if (isRegisteredService(service)) return service;

        service.onLoad();
        if (service.getListeners() != null)
            listenerService.registerEvent();

        if (service.getCommands() != null)
            commandService.registerCommand(service.getCommands().toArray(new Command[0]));

        if (service.getPermissions() != null)
            service.getPermissions().forEach(permission -> Bukkit.getPluginManager().addPermission(permission));

        this.services.add(service);

        return service;
    }

    public void loadService(@Nonnull Service... services) {
        for (Service service : services) {
            loadService(service);
        }
    }

    public void unloadService(@Nonnull Service service) {
        if (!isRegisteredService(service)) return;

        service.onUnload();
        if (service.getListeners() != null)
            service.getListeners().forEach(HandlerList::unregisterAll);

        if (service.getCommands() != null)
            commandService.unregisterCommand(service.getCommands().toArray(new Command[0]));

        if (service.getPermissions() != null)
            service.getPermissions().forEach(permission -> Bukkit.getPluginManager().removePermission(permission));

        this.services.remove(service);
    }

    public void unloadService(@Nonnull Service... services) {
        for (Service service : services) {
            unloadService(service);
        }
    }

    public void unloadAllService() {
        for (Service service : services) {
            unloadService(service);
        }
    }

    public void startServices() {

    }

    public void stopServices() {

    }

    public Set<Service> getServices() {
        return services;
    }

    @Nullable
    public Service getService(@Nonnull Class<? extends Service> serviceClass) {
        return services.stream()
                .filter(service -> service.getClass().equals(serviceClass))
                .findFirst()
                .orElse(null);
    }

    @Nullable
    public Service getService(@Nonnull String serviceClassName) {
        return services.stream()
                .filter(service -> service.getClass().getName().equals(serviceClassName))
                .findFirst()
                .orElse(null);
    }

    public boolean isRegisteredService(@Nonnull Service service) {
        return services.stream()
                .anyMatch(s -> s.getClass().getName().equals(service.getClass().getName()));
    }

    public boolean isRegisteredService(@Nonnull String serviceClassName) {
        return services.stream()
                .anyMatch(service -> service.getClass().getName().equals(serviceClassName));
    }

    public ListenerService getListenerService() {
        return listenerService;
    }

    public CommandService getCommandService() {
        return commandService;
    }
}
