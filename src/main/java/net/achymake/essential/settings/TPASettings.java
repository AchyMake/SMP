package net.achymake.essential.settings;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TPASettings {
    public static boolean hasTask(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("tpa-task");
    }
    public static Integer getTask(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getInt("tpa-task");
    }
    public static boolean hasTPARequest(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("tpa-request-sent");
    }
    public static boolean hasSentTPARequest(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("tpa-request-sent");
    }
    public static void addTPARequest(OfflinePlayer offlinePlayer,OfflinePlayer offlinePlayerTarget, Integer taskID){
        File fileSender = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configSender = YamlConfiguration.loadConfiguration(fileSender);
        File fileTarget = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayerTarget.getUniqueId()+".yml");
        FileConfiguration configTarget = YamlConfiguration.loadConfiguration(fileTarget);
        configSender.set("tpa-task",taskID);
        configSender.set("tpa-request-sent",offlinePlayerTarget.getUniqueId().toString());
        configTarget.set("tpa-request-from",offlinePlayer.getUniqueId().toString());
        try {
            configSender.save(fileSender);
            configTarget.save(fileTarget);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static Player getTPAFrom(OfflinePlayer offlinePlayer){
        return Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(offlinePlayer).getString("tpa-request-from")));
    }
    public static Player getTPASent(OfflinePlayer offlinePlayer){
        return Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(offlinePlayer).getString("tpa-request-sent")));
    }
    public static void removeTPARequest(OfflinePlayer offlinePlayer,OfflinePlayer offlinePlayerTarget){
        File fileSender = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configSender = YamlConfiguration.loadConfiguration(fileSender);
        File fileTarget = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayerTarget.getUniqueId()+".yml");
        FileConfiguration configTarget = YamlConfiguration.loadConfiguration(fileTarget);
        configSender.set("tpa-request-sent",null);
        configSender.set("tpa-task",null);
        configTarget.set("tpa-request-from",null);
        try {
            configSender.save(fileSender);
            configTarget.save(fileTarget);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}
