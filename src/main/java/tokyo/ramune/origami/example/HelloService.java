package tokyo.ramune.origami.example;

import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.Nullable;
import tokyo.ramune.origami.service.Service;
import tokyo.ramune.origami.system.Command;
import tokyo.ramune.origami.system.Listener;

import java.util.Set;

public class HelloService extends Service {
    public HelloService() {
        super(OrigamiPlugin.getPlugin(OrigamiPlugin.class));
    }

    @Override
    public void onStart() {
        getLogger().info("Hello World. This is HelloService!");
    }

    @Override
    public void onStop() {
        getLogger().info("Good Bye~~ (+ +)/");
    }

    @Nullable
    @Override
    public Set<Listener<? extends Event>> getListeners() {
        return Set.of(
                new Listener<>(PlayerJoinEvent.class,
                        event -> event.joinMessage(Component.text("Hello " + event.getPlayer().getName())),
                        EventPriority.NORMAL,
                        false)
        );
    }

    @Nullable
    @Override
    public Set<Command> getCommands() {
        return Set.of(
                new Command("hello",
                        args -> {
                            if (!args.getSender().hasPermission("command.hello")) {
                                args.getSender().sendMessage("Oops. You don't have permission to do this.");
                                return false;
                            }

                            args.getSender().sendMessage("Hello");
                            return true;
                        },
                        null)
        );
    }

    @Nullable
    @Override
    public Set<Permission> getPermissions() {
        return Set.of(
                new Permission("command.hello", PermissionDefault.OP)
        );
    }
}
