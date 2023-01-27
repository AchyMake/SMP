package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.settings.TPASettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitWithTPATask implements Listener {
    public PlayerQuitWithTPATask(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuitWithTPATask (PlayerQuitEvent event){
        if (!TPASettings.hasTask(event.getPlayer()))return;
        TPASettings.getTPASent(event.getPlayer()).sendMessage(ChatColor.translateAlternateColorCodes('&',event.getPlayer().getName()+"&c has left canceling tpa request"));
        TPASettings.removeTPARequest(event.getPlayer(),TPASettings.getTPASent(event.getPlayer()));
        TPASettings.getTPASent(event.getPlayer()).sendMessage(ChatColor.translateAlternateColorCodes('&',"&cTpa request has been cancelled"));
    }
}