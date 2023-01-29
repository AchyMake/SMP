package net.achymake.essential.listeners.bucket;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import java.text.MessageFormat;

public class BucketEmptyNotify implements Listener {
    public BucketEmptyNotify(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLoginSetup (PlayerBucketEmptyEvent event){
        if (!Config.get().getStringList("notifications.bucket-place").contains(event.getBucket().toString()))return;
        for (String messages : Config.get().getStringList("notifications.message")){
            Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages,event.getPlayer().getName(),event.getBucket().toString(),event.getBlockClicked().getWorld().getName(),event.getBlockClicked().getLocation().getBlockX(),event.getBlockClicked().getLocation().getBlockY(),event.getBlockClicked().getLocation().getBlockZ())),"essence.notify.bucket-place");
        }
    }
}