package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.MessageFormat;

public class PlayerJoinMessage implements Listener {
    public PlayerJoinMessage(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinMessage (PlayerJoinEvent event){
        if (PlayerConfig.get(event.getPlayer()).getBoolean("vanished"))return;
        if (Config.get().getBoolean("join-message.enable")){
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(Config.get().getString("join-message.join"),event.getPlayer().getName())));
        }else{
            if (event.getPlayer().hasPermission("essential.join-message")){
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(Config.get().getString("join-message.join"),event.getPlayer().getName())));
            }else{
                event.setJoinMessage(null);
            }
        }
    }
}