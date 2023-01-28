package net.achymake.essential.settings;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class LocationSettings {
    private static void setLastLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
        config.set("last-location.world",location.getWorld().getName());
        config.set("last-location.x",location.getX());
        config.set("last-location.y",location.getY());
        config.set("last-location.z",location.getZ());
        config.set("last-location.yaw",location.getYaw());
        config.set("last-location.pitch",location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void RTPLocation(Player player){
        World rtpWorld = Bukkit.getWorld(Config.get().getString("commands.rtp.world"));
        Random random = new Random();
        Location location = rtpWorld.getHighestBlockAt(random.nextInt(0, Config.get().getInt("commands.rtp.spread")),random.nextInt(0, Config.get().getInt("commands.rtp.spread"))).getLocation();
        if (Config.get().getStringList("commands.rtp.safe-materials").contains(location.getBlock().getType().toString())){
            location.getChunk().load();
            if (location.getBlock().getType().equals(Material.GRASS)){
                setLastLocation(player);
                player.teleport(location.add(0.5,0,0.5));
            }else{
                setLastLocation(player);
                player.teleport(location.add(0.5,1,0.5));
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleported to X:&f "+location.getBlockX()+"&6 Z:&f "+location.getBlockZ()));
        }else{
            RTPLocation(player);
        }
    }
}
