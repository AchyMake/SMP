package net.achymake.essential.settings;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Economy {
    public static double getEconomy(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getDouble("account");
    }
    public static void addEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        double newAmount = amount + config.getDouble("account");
        config.set("account",newAmount);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void removeEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        double newAmount = config.getDouble("account") - amount;
        config.set("account",newAmount);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void setEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("account",amount);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static void resetEconomy(OfflinePlayer offlinePlayer){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("account",0.0);
        try {
            config.save(file);
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
