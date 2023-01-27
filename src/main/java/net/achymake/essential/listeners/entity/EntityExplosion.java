package net.achymake.essential.listeners.entity;

import net.achymake.essential.Essential;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Random;

public class EntityExplosion implements Listener {
    public EntityExplosion(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (EntityExplodeEvent event){
        if (!WorldConfig.get().getBoolean(event.getEntity().getWorld().getName() + ".settings.cancel-entity." + event.getEntity().getType()))return;
        event.setCancelled(true);
        for (Player players : Bukkit.getOnlinePlayers()){
            players.playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE,new Random().nextFloat(0.75F,1.00F),new Random().nextFloat(0.75F,1.25F));
        }
    }
}