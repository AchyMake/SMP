package net.achymake.essential.listeners.chat;

import net.achymake.essential.Essential;
import net.achymake.essential.settings.PlayerSettings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatMuted implements Listener {
    public PlayerChatMuted(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatMuted (AsyncPlayerChatEvent event){
        if (!PlayerSettings.isMuted(event.getPlayer()))return;
        event.setCancelled(true);
        if (event.isCancelled()){
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to chat")));
        }
    }
}