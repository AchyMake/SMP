package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WorldConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "world.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        Bukkit.getScheduler().runTaskLater(Essential.instance, new Runnable() {
            @Override
            public void run() {
                if (!configFile.exists()){
                    for (World world : Bukkit.getWorlds()){
                        get().addDefault(world.getName()+".environment",world.getEnvironment().toString());
                        get().addDefault(world.getName()+".seed",world.getSeed());
                        get().addDefault(world.getName()+".settings.lava-flow",true);
                    }
                    get().options().copyDefaults(true);
                    save();
                }else{
                    for (String world : get().getKeys(false)){
                        Essential.instance.sendMessage("&eLoading &f" + world );
                        WorldCreator worldCreator = new WorldCreator(world);
                        worldCreator.environment(World.Environment.valueOf(get().getString(world+".environment")));
                        worldCreator.seed(get().getLong(world+".seed"));
                        worldCreator.createWorld();
                        Essential.instance.sendMessage(world + "&e Successfully loaded");
                    }
                }
            }
        },100);
    }
    public static FileConfiguration get(){
        return config;
    }
    public static void save(){
        try {
            config.save(configFile);
        }catch (IOException e){
            System.out.println("Couldn't save 'world.yml'");
        }
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}