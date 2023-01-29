package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    public static void setup(){
        for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()){
            create(offlinePlayer);
        }
    }
    public static boolean exist(OfflinePlayer offlinePlayer){
        return new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml").exists();
    }
    public static FileConfiguration get(OfflinePlayer offlinePlayer){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        return YamlConfiguration.loadConfiguration(file);
    }
    public static void create(OfflinePlayer offlinePlayer){
        File folder = new File(Essential.instance.getDataFolder(), "userdata");
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!file.exists()){
            configuration.set("new",true);
            configuration.set("name",offlinePlayer.getName());
            configuration.set("account",Config.get().getDouble("economy.starting-balance"));
            configuration.set("max-homes",1);
            configuration.set("pvp",true);
            configuration.createSection("homes");
            try {
                configuration.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            if (!configuration.getString("name").equals(offlinePlayer.getName())){
                configuration.set("name",offlinePlayer.getName());
                try {
                    configuration.save(file);
                } catch (IOException e) {
                    Essential.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
    public static void reload(){
        for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()){
            File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            if (file.exists()){
                try {
                    configuration.load(file);
                } catch (IOException | InvalidConfigurationException e) {
                    Essential.instance.sendMessage(e.getMessage());
                }
            }else{
                create(offlinePlayer);
            }
        }
    }
    public static void toggle(OfflinePlayer offlinePlayer, String type){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (configuration.getBoolean(type)){
            configuration.set(type,null);
            try {
                configuration.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            configuration.set(type,true);
            try {
                configuration.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }
    }
    public static void setString(OfflinePlayer offlinePlayer,String type, String value){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(type,value);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void setInt(OfflinePlayer offlinePlayer,String type, int value){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        int amount = configuration.getInt(type)+value;
        configuration.set(type,amount);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void setDouble(OfflinePlayer offlinePlayer,String type, double value){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(type,value);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void setFloat(OfflinePlayer offlinePlayer,String type, float value){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(type,value);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void setLocation(Player player, String locationName){
        Location location = player.getLocation();
        setString(player,locationName+".world",location.getWorld().getName());
        setDouble(player,locationName+".x",location.getX());
        setDouble(player,locationName+".y",location.getY());
        setDouble(player,locationName+".z",location.getZ());
        setFloat(player,locationName+".yaw",location.getYaw());
        setFloat(player,locationName+".pitch",location.getPitch());
    }
}