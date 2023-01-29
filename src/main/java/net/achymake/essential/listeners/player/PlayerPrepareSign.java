package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class PlayerPrepareSign implements Listener {
    public PlayerPrepareSign(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (SignChangeEvent event){
        if (!event.getPlayer().hasPermission("essentials.chatcolor.sign"))return;
        for (int i = 0; i < event.getLines().length; i++){
            if (event.getLine(i).contains("&")){
                event.setLine(i, ChatColor.translateAlternateColorCodes('&',event.getLine(i)));
            }
        }
    }
}