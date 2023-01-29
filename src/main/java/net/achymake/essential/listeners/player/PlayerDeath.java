package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    public PlayerDeath(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerDeathEvent event){
        Location location = event.getEntity().getLocation();
        PlayerConfig.setString(event.getEntity(),"death-location.world",event.getEntity().getWorld().getName());
        PlayerConfig.setDouble(event.getEntity(),"death-location.x",location.getX());
        PlayerConfig.setDouble(event.getEntity(),"death-location.y",location.getY());
        PlayerConfig.setDouble(event.getEntity(),"death-location.z",location.getZ());
        PlayerConfig.setFloat(event.getEntity(),"death-location.yaw",location.getYaw());
        PlayerConfig.setFloat(event.getEntity(),"death-location.pitch",location.getPitch());
        PlayerConfig.toggle(event.getEntity(),"dead");
    }
}