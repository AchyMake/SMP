package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ExperienceConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "experience.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        if (!configFile.exists()){
            get().addDefault("settings.player-throw-arrows.enable",true);
            get().addDefault("settings.player-throw-arrows.max-level",30);
            get().addDefault("settings.level-up.particle.enable",true);
            get().addDefault("settings.level-up.particle.type","TOTEM");
            get().addDefault("settings.level-up.particle.count",25);
            get().addDefault("settings.level-up.particle.offsetX",0.3);
            get().addDefault("settings.level-up.particle.offsetY",0.7);
            get().addDefault("settings.level-up.particle.offsetZ",0.3);
            get().addDefault("settings.level-up.sound.enable",true);
            get().addDefault("settings.level-up.sound.type","ENTITY_VEX_AMBIENT");
            get().addDefault("settings.level-up.sound.volume","2.5F");
            get().addDefault("settings.level-up.sound.pitch","1.0F");
            get().addDefault("settings.level-down.particle.enable",true);
            get().addDefault("settings.level-down.particle.type","SOUL");
            get().addDefault("settings.level-down.particle.count",25);
            get().addDefault("settings.level-down.particle.offsetX",0.3);
            get().addDefault("settings.level-down.particle.offsetY",0.7);
            get().addDefault("settings.level-down.particle.offsetZ",0.3);
            get().addDefault("settings.level-down.sound.enable",true);
            get().addDefault("settings.level-down.sound.type","ENTITY_VEX_AMBIENT");
            get().addDefault("settings.level-down.sound.volume","2.5F");
            get().addDefault("settings.level-down.sound.pitch","1.0F");
            get().addDefault("0.max-health", 20.0);
            get().addDefault("1.max-health", 20.0);
            get().addDefault("2.max-health", 20.0);
            get().addDefault("3.max-health", 22.0);
            get().addDefault("4.max-health", 22.0);
            get().addDefault("5.max-health", 22.0);
            get().addDefault("6.max-health", 24.0);
            get().addDefault("7.max-health", 24.0);
            get().addDefault("8.max-health", 24.0);
            get().addDefault("9.max-health", 26.0);
            get().addDefault("10.max-health", 26.0);
            get().addDefault("11.max-health", 26.0);
            get().addDefault("12.max-health", 28.0);
            get().addDefault("13.max-health", 28.0);
            get().addDefault("14.max-health", 28.0);
            get().addDefault("15.max-health", 30.0);
            get().addDefault("16.max-health", 30.0);
            get().addDefault("17.max-health", 30.0);
            get().addDefault("18.max-health", 32.0);
            get().addDefault("19.max-health", 32.0);
            get().addDefault("20.max-health", 32.0);
            get().addDefault("21.max-health", 34.0);
            get().addDefault("22.max-health", 34.0);
            get().addDefault("23.max-health", 34.0);
            get().addDefault("24.max-health", 36.0);
            get().addDefault("25.max-health", 36.0);
            get().addDefault("26.max-health", 36.0);
            get().addDefault("27.max-health", 38.0);
            get().addDefault("28.max-health", 38.0);
            get().addDefault("29.max-health", 38.0);
            get().addDefault("30.max-health", 40.0);
            get().options().copyDefaults(true);
            save();
        }
    }
    public static FileConfiguration get(){
        return config;
    }
    public static void save(){
        try {
            config.save(configFile);
        }catch (IOException e){
            System.out.println("Couldn't save 'experience.yml'");
        }
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
