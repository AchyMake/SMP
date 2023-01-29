package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "location.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        get().options().copyDefaults(true);
        save();
    }
    public static void save(){
        try {
            config.save(configFile);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static FileConfiguration get(){
        return config;
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public static boolean locationExist(String location){
        return get().getKeys(false).contains(location);
    }
    public static void setLocation(Player player, String locationName){
        Location location = player.getLocation();
        LocationConfig.get().set(locationName+".world",location.getWorld().getName());
        LocationConfig.get().set(locationName+".x",location.getX());
        LocationConfig.get().set(locationName+".y",location.getY());
        LocationConfig.get().set(locationName+".z",location.getZ());
        LocationConfig.get().set(locationName+".yaw",location.getYaw());
        LocationConfig.get().set(locationName+".pitch",location.getPitch());
        LocationConfig.save();
    }
    public static Location getLocation(String location){
        String worldName = get().getString(location+".world");
        double x = get().getDouble(location+".x");
        double y = get().getDouble(location+".y");
        double z = get().getDouble(location+".z");
        float yaw = get().getLong(location+".yaw");
        float pitch = get().getLong(location+".pitch");
        return new Location(Bukkit.getServer().getWorld(worldName),x,y,z,yaw,pitch);
    }
}