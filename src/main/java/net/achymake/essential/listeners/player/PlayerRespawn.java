package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;
import java.io.IOException;

public class PlayerRespawn implements Listener {
    public PlayerRespawn(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if (!hasDied(player))return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Death location:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6World:&f "+getDeathLocation(player).getWorld().getEnvironment().name().toLowerCase()+" &6X:&f "+getDeathLocation(player).getBlockX()+" &6Y:&f "+getDeathLocation(player).getBlockY()+" &6Z:&f "+getDeathLocation(player).getBlockZ()));
        remove(player);
    }
    private Location getDeathLocation(Player player){
        String world = PlayerConfig.get(player).getString("death-location.world");
        double x = PlayerConfig.get(player).getDouble("death-location.x");
        double y = PlayerConfig.get(player).getDouble("death-location.y");
        double z = PlayerConfig.get(player).getDouble("death-location.z");
        float yaw = PlayerConfig.get(player).getLong("death-location.yaw");
        float pitch = PlayerConfig.get(player).getLong("death-location.pitch");
        return new Location(player.getServer().getWorld(world),x,y,z,yaw,pitch);
    }
    private static boolean hasDied(Player player){
        return PlayerConfig.get(player).getBoolean("dead");
    }
    private void remove(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("dead",null);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}