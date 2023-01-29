package net.achymake.essential.settings;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TPASettings {
    public static boolean hasTask(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("tpa-task");
    }
    public static Integer getTask(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getInt("tpa-task");
    }
    public static boolean hasTPARequest(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("tpa-request-from");
    }
    public static boolean hasSentTPARequest(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("tpa-request-sent");
    }
    public static void addTPARequest(OfflinePlayer offlinePlayer,OfflinePlayer offlinePlayerTarget, Integer taskID){
        PlayerConfig.setInt(offlinePlayer,"tpa-task",taskID);
        PlayerConfig.setString(offlinePlayer,"tpa-request-send",offlinePlayerTarget.getUniqueId().toString());
        PlayerConfig.setString(offlinePlayerTarget,"tpa-request-from",offlinePlayer.getUniqueId().toString());
    }
    public static Player getTPAFrom(OfflinePlayer offlinePlayer){
        return Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(offlinePlayer).getString("tpa-request-from")));
    }
    public static Player getTPASent(OfflinePlayer offlinePlayer){
        return Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(offlinePlayer).getString("tpa-request-sent")));
    }
    public static void removeTPARequest(OfflinePlayer offlinePlayer,OfflinePlayer offlinePlayerTarget){
        Bukkit.getScheduler().cancelTask(PlayerConfig.get(offlinePlayer).getInt("tpa-task"));
        PlayerConfig.setString(offlinePlayer,"tpa-request-sent",null);
        PlayerConfig.setString(offlinePlayer,"tpa-task",null);
        PlayerConfig.setString(offlinePlayerTarget,"tpa-request-from",null);
    }
}
