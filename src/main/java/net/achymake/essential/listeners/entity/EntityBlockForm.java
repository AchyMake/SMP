package net.achymake.essential.listeners.entity;

import net.achymake.essential.Essential;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;

public class EntityBlockForm implements Listener {
    public EntityBlockForm(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (EntityBlockFormEvent event) {
        if (!WorldConfig.get().getBoolean(event.getEntity().getWorld().getName() + ".settings.cancel-entity." + event.getEntity().getType()))return;
        event.setCancelled(true);
    }
}