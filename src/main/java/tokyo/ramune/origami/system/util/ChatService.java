package tokyo.ramune.origami.system.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.origami.system.service.Service;

public class ChatService extends Service {
    private static final RateLimiter<CommandSender> rateLimiter = new RateLimiter<>(1);
    private static String prefix;

    public ChatService(@NotNull JavaPlugin plugin) {
        super(plugin);
    }

    public static void sendMessage(Player player, String message, boolean addPrefix) {
        player.sendMessage(addPrefix ? prefix : "" + message);
    }

    public static void sendMessage(CommandSender sender, String message, boolean addPrefix) {
        sender.sendMessage(addPrefix ? prefix : "" + message);
    }

    public static void sendMessage(Player player, String message, boolean addPrefix, boolean rateLimited) {
        if (rateLimited && !rateLimiter.tryAcquire(player))
            return;

        sendMessage(player, message, addPrefix);
    }

    public static void sendMessage(CommandSender sender, String message, boolean addPrefix, boolean rateLimited) {
        if (rateLimited && !rateLimiter.tryAcquire(sender))
            return;

        sendMessage(sender, message, addPrefix);
    }

    public void resetPrefix() {
        prefix = getPlugin().getName();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        ChatService.prefix = prefix;
    }

    @Override
    public void onLoad() {
        resetPrefix();
    }
}
