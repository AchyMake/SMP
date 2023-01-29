package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitWithTPATask implements Listener {
    public PlayerQuitWithTPATask(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuitWithTPATask (PlayerQuitEvent event){
        if (!PlayerConfig.get(event.getPlayer()).getKeys(false).contains("tpa-task"))return;
        Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(event.getPlayer()).getString("tpa-request-sent"))).sendMessage(ChatColor.translateAlternateColorCodes('&',event.getPlayer().getName()+"&c has left canceling tpa request"));
        Bukkit.getScheduler().cancelTask(PlayerConfig.get(event.getPlayer()).getInt("tpa-task"));
        PlayerConfig.setString(Bukkit.getOfflinePlayer(UUID.fromString(PlayerConfig.get(event.getPlayer()).getString("tpa-request-sent"))),"tpa-request-from",null);
        PlayerConfig.setString(event.getPlayer(),"tpa-request-sent",null);
        PlayerConfig.setString(event.getPlayer(),"tpa-task",null);
    }
}