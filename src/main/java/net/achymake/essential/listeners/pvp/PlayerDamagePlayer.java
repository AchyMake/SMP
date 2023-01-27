package net.achymake.essential.listeners.pvp;

import net.achymake.essential.Essential;
import net.achymake.essential.settings.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamagePlayer implements Listener {
    public PlayerDamagePlayer(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDamageEntity (EntityDamageByEntityEvent event){
        if (!event.getDamager().getType().equals(EntityType.PLAYER))return;
        if (!event.getEntity().getType().equals(EntityType.PLAYER))return;
        Player player = (Player) event.getDamager();
        Player target = (Player) event.getEntity();
        if (!PlayerSettings.hasPVP(player)){
            event.setCancelled(true);
        } else if (!PlayerSettings.hasPVP(target)){
            event.setCancelled(true);
        }
    }
}