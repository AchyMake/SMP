package net.achymake.essential.api;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class EconomyProvider {
    public static double getEconomy(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getDouble("account");
    }
    public static void addEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        double newAmount = amount + getEconomy(offlinePlayer);
        configuration.set("account",newAmount);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void removeEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        double newAmount = getEconomy(offlinePlayer) - amount;
        configuration.set(offlinePlayer.getUniqueId()+".account",newAmount);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void setEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(offlinePlayer.getUniqueId()+".account",amount);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static String getFormat(Double value) {
        String format = Config.get().getString("economy.format");
        DecimalFormat balance = new DecimalFormat(format);
        String formatted = balance.format(value);
        return Config.get().getString("economy.currency")+formatted;
    }
}
