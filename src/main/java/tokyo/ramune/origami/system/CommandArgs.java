package tokyo.ramune.origami.system;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CommandArgs {
    private final CommandSender sender;
    private final String[] args;

    public CommandArgs(@Nonnull CommandSender sender, @Nonnull String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    @Nullable
    public Player getPlayer() {
        return (Player) sender;
    }

    public String[] getArgs() {
        return args;
    }

    public boolean instanceOfPlayer() {
        return sender instanceof Player;
    }

    public boolean instanceOfConsole() {
        return sender instanceof ConsoleCommandSender;
    }

    public boolean instanceOfCommandBlock() {
        return sender instanceof BlockCommandSender;
    }
}
