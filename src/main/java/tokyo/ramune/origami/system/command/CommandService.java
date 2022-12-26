package tokyo.ramune.origami.system.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.origami.system.service.Service;

import javax.annotation.Nonnull;
import java.util.*;

public class CommandService extends Service {
    private Set<String> registerCommands;

    public CommandService(@Nonnull JavaPlugin plugin) {
        super(plugin);
    }

    public void registerCommand(@Nonnull Command... commands) {
        for (Command command : commands) {
            if (isRegistered(command))
                continue;

            Bukkit.getServer().getCommandMap().register(command.getName(), new org.bukkit.command.Command(command.getName()) {
                @Override
                public boolean execute(@Nonnull CommandSender sender, @Nonnull String commandLabel, @Nonnull String[] args) {
                    return command.getOnCommand() != null && command.getOnCommand().apply(new CommandArgs(sender, args));
                }

                @Override
                public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                    return command.getOnTabComplete() == null ? new ArrayList<>() : command.getOnTabComplete().apply(new CommandArgs(sender, args));
                }
            });
            registerCommands.add(command.getName());
        }
    }

    public void unregisterCommand(@Nonnull Command... commands) {
        for (Command command : commands) {
            org.bukkit.command.Command registeredCommand = Bukkit.getServer().getCommandMap().getCommand(command.getName());
            if (registeredCommand == null)
                continue;

            registeredCommand.unregister(Bukkit.getServer().getCommandMap());
        }
    }

    public boolean isRegistered(@Nonnull Command command) {
        return Bukkit.getCommandMap().getCommand(command.getName()) != null;
    }

    @Override
    public void onLoad() {
        registerCommands = new HashSet<>();
    }

    @Override
    public void onUnload() {
        for (String registerCommand : registerCommands) {
            Objects.requireNonNull(Bukkit.getServer().getCommandMap().getCommand(registerCommand)).unregister(Bukkit.getServer().getCommandMap());
        }
    }
}
