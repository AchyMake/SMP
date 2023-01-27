package net.achymake.essential.settings;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class VanishSettings {
    public static boolean isVanished(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getBoolean("vanished");
    }
    public static void toggleVanish(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getBoolean("vanished")){
            for (Player players : Bukkit.getOnlinePlayers()){
                players.showPlayer(Essential.instance,player);
            }
            Essential.vanished.remove(player);
            player.setAllowFlight(false);
            player.setCollidable(true);
            player.setInvulnerable(false);
            player.setCanPickupItems(true);
            player.setSleepingIgnored(false);
            player.setSilent(false);
            for (Player vanishedPlayers : Essential.vanished){
                player.hidePlayer(Essential.instance,vanishedPlayers);
            }
            config.set("vanished",false);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You are no longer vanished"));
        }else{
            for (Player players : Bukkit.getOnlinePlayers()){
                players.hidePlayer(Essential.instance,player);
            }
            Essential.vanished.add(player);
            player.setAllowFlight(true);
            player.setCollidable(false);
            player.setInvulnerable(true);
            player.setCanPickupItems(false);
            player.setSleepingIgnored(true);
            player.setSilent(true);
            for (Player vanishPlayers : Essential.vanished){
                player.showPlayer(Essential.instance,vanishPlayers);
                vanishPlayers.showPlayer(Essential.instance,player);
            }
            config.set("vanished",true);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You are now vanished"));
        }
    }
    public static void toggleVanishOffline(OfflinePlayer offlinePlayer){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getBoolean("vanished")){
            config.set("vanished",false);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            config.set("vanished",true);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }
    }
}
