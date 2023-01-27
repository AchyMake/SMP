package net.achymake.essential.listeners.entity;

import net.achymake.essential.Essential;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {
    public EntitySpawn(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(EntitySpawnEvent event){
        if (!WorldConfig.get().getBoolean(event.getEntity().getWorld().getName()+".settings.cancel-spawn."+event.getEntity().getType()))return;
        event.setCancelled(true);
    }
}