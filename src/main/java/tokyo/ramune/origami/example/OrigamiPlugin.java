package tokyo.ramune.origami.example;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.origami.system.service.ManagerService;

public final class OrigamiPlugin extends JavaPlugin {
    private ManagerService serviceManager;

    @Override
    public void onEnable() {
        // Require to use services
        serviceManager = new ManagerService(this);

        // This is How to load service and start
        serviceManager.loadService(new HelloService());
    }

    @Override
    public void onDisable() {
        // add this if you want
        serviceManager.unloadSystemServices();
    }

    public ManagerService getServiceManager() {
        return serviceManager;
    }
}
