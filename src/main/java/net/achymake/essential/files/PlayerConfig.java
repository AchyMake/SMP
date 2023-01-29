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
            configuration.set("account",0.0);
            configuration.set("group","default");
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
    public static void setHome(Player player, String homeName){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
        configuration.set("homes."+homeName+".world",location.getWorld().getName());
        configuration.set("homes."+homeName+".x",location.getX());
        configuration.set("homes."+homeName+".y",location.getY());
        configuration.set("homes."+homeName+".z",location.getZ());
        configuration.set("homes."+homeName+".yaw",location.getYaw());
        configuration.set("homes."+homeName+".pitch",location.getPitch());
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}