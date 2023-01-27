package net.achymake.essential.files;

import net.achymake.essential.Essential;
import net.achymake.essential.tablist.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TablistConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "tablist.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        if (!configFile.exists()){
            List<String> header = new ArrayList<>();
            header.add("&eE&6ssential");
            header.add("&6E&es&6sential");
            header.add("&6Es&es&6ential");
            header.add("&6Ess&ee&6ntial");
            header.add("&6Esse&en&6tial");
            header.add("&6Essen&et&6ial");
            header.add("&6Essent&ei&6al");
            header.add("&6Essenti&ea&6l");
            header.add("&6Essentia&el");
            header.add("&6Essenti&ea&6l");
            header.add("&6Essent&ei&6al");
            header.add("&6Essen&et&6ial");
            header.add("&6Esse&en&6tial");
            header.add("&6Ess&ee&6ntial");
            header.add("&6Es&es&6ential");
            header.add("&6E&es&6sential");
            List<String> footer = new ArrayList<>();
            footer.add("&e{0}&7/&e{1}");
            get().addDefault("header.tick",3);
            get().addDefault("header.list",header);
            get().addDefault("footer.tick",20);
            get().addDefault("footer.list",footer);
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
        for (Integer tasks : Essential.tasks){
            Bukkit.getScheduler().cancelTask(tasks);
        }
        Tablist.start(Essential.instance);
    }
}