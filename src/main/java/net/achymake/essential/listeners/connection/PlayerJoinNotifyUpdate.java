package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.version.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinNotifyUpdate implements Listener {
    public PlayerJoinNotifyUpdate(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinNotifyUpdate (PlayerLoginEvent event){
        if (!event.getPlayer().hasPermission("essential.reload"))return;
        UpdateChecker.sendMessage(event.getPlayer());
    }
}