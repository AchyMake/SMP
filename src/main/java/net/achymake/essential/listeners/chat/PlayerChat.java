package net.achymake.essential.listeners.chat;

import me.clip.placeholderapi.PlaceholderAPI;
import net.achymake.essential.Essential;
import net.milkbowl.vault.economy.AbstractEconomy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    public PlayerChat(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatFormat (AsyncPlayerChatEvent event){
        if (event.getPlayer().hasPermission("essential.chatcolor")){
            event.setFormat(ChatColor.translateAlternateColorCodes('&', prefix(event.getPlayer()) + name(event.getPlayer()) + suffix(event.getPlayer())+"&r: "+event.getMessage()));
        }else{
            event.setFormat(ChatColor.translateAlternateColorCodes('&', prefix(event.getPlayer()) + name(event.getPlayer()) + suffix(event.getPlayer())+"&r: ")+event.getMessage());
        }
    }
    private String prefix(Player player) {
        if (PlaceholderAPI.isRegistered("luckperms")){
            return PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        }else{
            return "";
        }
    }
    private String name(Player player) {
        return PlaceholderAPI.setPlaceholders(player, "%essential_player%");
    }
    private String suffix(Player player) {
        if (PlaceholderAPI.isRegistered("luckperms")){
            return PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");
        }else{
            return "";
        }
    }
}