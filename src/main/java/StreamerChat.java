import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class StreamerChat implements Listener {
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onStreamerSpeak(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();
        String chatMessage = e.getMessage();
        String colorMessage = ChatColor.translateAlternateColorCodes('&', chatMessage);
        if (Config.streamerToggle.containsKey(playerUUID)) {
            String name = ChatColor.translateAlternateColorCodes('&', "&9"+e.getPlayer().getDisplayName());
            Bukkit.broadcast(Config.streamerPrefix + " "
                    + name + ": " +
                    ChatColor.LIGHT_PURPLE + colorMessage, "smidge.streamer");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onStreamerQuit(PlayerQuitEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.streamerToggle.remove(playerUUID);
    }
}
