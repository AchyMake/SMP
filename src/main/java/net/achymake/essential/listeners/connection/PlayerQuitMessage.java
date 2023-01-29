package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.MessageFormat;

public class PlayerQuitMessage implements Listener {
    public PlayerQuitMessage(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinNotifyUpdate (PlayerQuitEvent event){
        if (Config.get().getBoolean("join-message.enable")){
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(Config.get().getString("join-message.quit"),event.getPlayer().getName())));
        }else{
            if(event.getPlayer().hasPermission("essential.join-message")){
                event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(Config.get().getString("join-message.quit"),event.getPlayer().getName())));
            }else{
                event.setQuitMessage(null);
            }
        }
    }
}