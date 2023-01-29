package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.version.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinNotify implements Listener {
    public PlayerJoinNotify(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinNotifyUpdate (PlayerJoinEvent event){
        if (!event.getPlayer().hasPermission("essential.reload"))return;
        UpdateChecker.sendMessage(event.getPlayer());
    }
}