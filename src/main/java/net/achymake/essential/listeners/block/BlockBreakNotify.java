package net.achymake.essential.listeners.block;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.text.MessageFormat;

public class BlockBreakNotify implements Listener {
    public BlockBreakNotify(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (BlockBreakEvent event){
        if (!Config.get().getStringList("notifications.block-break").contains(event.getBlock().getType().toString()))return;
        for (String messages : Config.get().getStringList("notifications.message")){
            Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages,event.getPlayer().getName(),event.getBlock().getType().toString(),event.getBlock().getWorld().getName(),event.getBlock().getLocation().getBlockX(),event.getBlock().getLocation().getBlockY(),event.getBlock().getLocation().getBlockZ())),"essence.notify.block-break");
        }
    }
}