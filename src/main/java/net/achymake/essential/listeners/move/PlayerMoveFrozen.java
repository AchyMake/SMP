package net.achymake.essential.listeners.move;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveFrozen implements Listener {
    public PlayerMoveFrozen(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuitWithTPATask (PlayerMoveEvent event){
        if (!PlayerConfig.get(event.getPlayer()).getBoolean("frozen"))return;
        event.setCancelled(true);
        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to move")));
    }
}