package net.achymake.essential.listeners.pvp;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamagePlayerByArrow implements Listener {
    public PlayerDamagePlayerByArrow(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDamageEntityByArrow (EntityDamageByEntityEvent event){
        if (!event.getDamager().getType().equals(EntityType.ARROW))return;
        if (!event.getEntity().getType().equals(EntityType.PLAYER))return;
        Player target = (Player) event.getEntity();
        Arrow arrow = (Arrow) event.getDamager();
        if (arrow.getShooter() instanceof Player){
            Player player = (Player) arrow.getShooter();
            if (!PlayerConfig.get(player).getBoolean("pvp")){
                event.setCancelled(true);
            } else if (!PlayerConfig.get(target).getBoolean("pvp")){
                event.setCancelled(true);
            }
        }
    }
}