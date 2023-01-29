package net.achymake.essential.listeners.block;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.text.MessageFormat;

public class BlockPlaceNotify implements Listener {
    public BlockPlaceNotify(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLoginSetup (BlockPlaceEvent event){
        if (!Config.get().getStringList("notifications.block-place").contains(event.getBlockPlaced().getType().toString()))return;
        for (String messages : Config.get().getStringList("notifications.message")){
            Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages,event.getPlayer().getName(),event.getBlockPlaced().getType().toString(),event.getBlockPlaced().getWorld().getName(),event.getBlockPlaced().getLocation().getBlockX(),event.getBlockPlaced().getLocation().getBlockY(),event.getBlockPlaced().getLocation().getBlockZ())),"essence.notify.block-place");
        }
    }
}