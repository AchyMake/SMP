package net.achymake.essential.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlaceholderProvider extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "essential";
    }
    @Override
    public String getAuthor() {
        return "AchyMake";
    }
    @Override
    public String getVersion() {
        return Essential.instance.getDescription().getVersion();
    }
    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public boolean register() {
        return super.register();
    }
    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null){
            return "";
        }
        if (params.equals("player")) {
            if (PlayerConfig.get(player).getKeys(false).contains("custom-name")){
                return ChatColor.translateAlternateColorCodes('&',PlayerConfig.get(player).getString("custom-name"));
            }else{
                return PlayerConfig.get(player).getString("name");
            }
        }
        if (params.equals("vanished")) {
            return String.valueOf(Essential.vanished.contains(player));
        }
        if (params.equals("online_players")) {
            return String.valueOf(Bukkit.getServer().getOnlinePlayers().size()-Essential.vanished.size());
        }
        return super.onRequest(player, params);
    }
}