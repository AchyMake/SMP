package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "motd.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        if (!configFile.exists()){
            get().addDefault("command.announcement","&6[&eServer&6] {0}");
            get().addDefault("command.anvil","&6Opened&f anvil");
            get().addDefault("command.back","&6Teleporting&f back");
            get().addDefault("command.back-death","&6Teleporting&f death location");
            get().addDefault("command.back-death-removal","&6Removed death location");
            get().addDefault("command.balance","&6Balance: {0}");
            get().addDefault("command.balance-others","{0}&6 balance: {1}");
            get().addDefault("command.error-target-offline","{0}&c is offline");
            get().addDefault("command.error-target-null","{0}&c has never joined");
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