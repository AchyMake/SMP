package net.achymake.essential.listeners.entity;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;

public class PlayerDeath implements Listener {
    public PlayerDeath(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        setDeathLocation(player);
    }
    private void setDeathLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
        config.set("dead",true);
        config.set("death-location.world",location.getWorld().getName());
        config.set("death-location.x",location.getX());
        config.set("death-location.y",location.getY());
        config.set("death-location.z",location.getZ());
        config.set("death-location.yaw",location.getYaw());
        config.set("death-location.pitch",location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}