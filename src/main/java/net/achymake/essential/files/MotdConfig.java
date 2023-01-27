package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MotdConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "motd.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        if (!configFile.exists()){
            get().options().copyDefaults(true);
            save();
        }
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
}