package net.achymake.essential.settings;

import net.achymake.essential.Essential;
import net.achymake.essential.files.LocationConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerSettings {
    public static boolean hasDeathLocation(Player player){
        return PlayerConfig.get(player).getKeys(false).contains("death-location");
    }
    public static Location getDeathLocation(Player player){
        World world = player.getServer().getWorld(PlayerConfig.get(player).getString("death-location.world"));
        double x = PlayerConfig.get(player).getDouble("death-location.x");
        double y = PlayerConfig.get(player).getDouble("death-location.y");
        double z = PlayerConfig.get(player).getDouble("death-location.z");
        float yaw = PlayerConfig.get(player).getLong("death-location.yaw");
        float pitch = PlayerConfig.get(player).getLong("death-location.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
    public static void setDeathLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
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
    public static void removeDeathLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("death-location",null);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static boolean hasLastLocation(Player player){
        return PlayerConfig.get(player).getKeys(false).contains("last-location");
    }
    public static Location getLastLocation(Player player){
        World world = player.getServer().getWorld(PlayerConfig.get(player).getString("last-location.world"));
        double x = PlayerConfig.get(player).getDouble("last-location.x");
        double y = PlayerConfig.get(player).getDouble("last-location.y");
        double z = PlayerConfig.get(player).getDouble("last-location.z");
        float yaw = PlayerConfig.get(player).getLong("last-location.yaw");
        float pitch = PlayerConfig.get(player).getLong("last-location.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
    public static void setLastLocation(Player player){
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
    public static boolean hasLastWhisper(Player player){
        return PlayerConfig.get(player).getKeys(false).contains("last-whisper");
    }
    public static Player getLastWhisper(Player player){
        return player.getServer().getPlayer(UUID.fromString(PlayerConfig.get(player).getString("last-whisper")));
    }
    public static void setLastWhisper(Player player, Player target){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("last-whisper",target.getUniqueId().toString());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static boolean hasPVP(Player player){
        return PlayerConfig.get(player).getBoolean("pvp");
    }
    public static boolean isFrozen(Player player){
        return PlayerConfig.get(player).getBoolean("frozen");
    }
    public static void toggleFreeze(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getBoolean("frozen")){
            config.set("frozen",false);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            config.set("frozen",true);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }
    }
    public static boolean isMuted(Player player){
        return PlayerConfig.get(player).getBoolean("muted");
    }
    public static void toggleMute(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getBoolean("muted")){
            config.set("muted",false);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            config.set("muted",true);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }
    }
    public static boolean isJailed(Player player){
        return PlayerConfig.get(player).getBoolean("jailed");
    }
}