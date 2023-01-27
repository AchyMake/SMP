package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    public static boolean exist(OfflinePlayer offlinePlayer){
        return new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml").exists();
    }
    public static FileConfiguration get(OfflinePlayer offlinePlayer){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        return YamlConfiguration.loadConfiguration(file);
    }
    public static void setup(OfflinePlayer offlinePlayer){
        File folder = new File(Essential.instance.getDataFolder(), "userdata");
        File configFile = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!configFile.exists()){
            config.set("name",offlinePlayer.getName());
            config.set("account", Config.get().getDouble("economy.starting-balance"));
            config.set("pvp",false);
            config.set("banned",false);
            config.set("banned-reason","");
            config.set("frozen",false);
            config.set("muted",false);
            config.set("jailed",false);
            config.set("new",true);
            config.options().copyDefaults(true);
            try {
                config.save(configFile);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            if (!config.getString("name").equals(offlinePlayer.getName())){
                config.set("name",offlinePlayer.getName());
                try {
                    config.save(configFile);
                } catch (IOException e) {
                    Essential.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
    public static void reload(){
        for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()){
            File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
            if (file.exists()){
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                try {
                    config.load(file);
                } catch (IOException | InvalidConfigurationException e) {
                    Essential.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
}