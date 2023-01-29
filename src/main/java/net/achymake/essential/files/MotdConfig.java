package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotdConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "motd.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        if (!configFile.exists()){
            List<String> motd = new ArrayList<>();
            motd.add("&6This is message of the day");
            motd.add("&6you can create any motd");
            motd.add("&6and execute with the command /motd name");
            get().addDefault("motd",motd);
            List<String> welcome = new ArrayList<>();
            welcome.add("&6Welcome&f {0}&6 to the server!");
            welcome.add("&6Hope you enjoy your adventure!");
            get().addDefault("welcome",welcome);
            List<String> welcomeBack = new ArrayList<>();
            welcomeBack.add("&6Welcome back&f {0}");
            welcomeBack.add("&6We missed you!");
            get().addDefault("welcome-back",welcomeBack);
            List<String> help = new ArrayList<>();
            help.add("&6Help:");
            help.add("&6- &fhttps://your-server.tebex.io/help");
            get().addDefault("help",help);
            List<String> rules = new ArrayList<>();
            rules.add("&6Rules:");
            rules.add("&6- &fhttps://your-server.tebex.io/rules");
            get().addDefault("rules",rules);
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