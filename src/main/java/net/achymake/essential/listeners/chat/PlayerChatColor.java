package net.achymake.essential.listeners.chat;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatColor implements Listener {
    public PlayerChatColor(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatColor (AsyncPlayerChatEvent event){
        if (!event.getPlayer().hasPermission("essential.chatcolor"))return;
        event.setMessage(ChatColor.translateAlternateColorCodes('&',event.getMessage()));
    }
}