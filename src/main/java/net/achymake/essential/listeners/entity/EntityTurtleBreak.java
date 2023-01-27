package net.achymake.essential.listeners.entity;

import net.achymake.essential.Essential;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EntityTurtleBreak implements Listener {
    public EntityTurtleBreak(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (EntityInteractEvent event){
        if (WorldConfig.get().getBoolean(event.getEntity().getWorld().getName()+".settings.turtle-egg-break"))return;
        if (!event.getBlock().getType().equals(Material.TURTLE_EGG))return;
        if (!event.getEntity().getType().equals(EntityType.ZOMBIE))return;
        event.setCancelled(true);
    }
}