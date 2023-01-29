package net.achymake.essential.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
        if (params.equals("player")) {
            return player.getName();
        }
        if (params.equals("vanished")) {
            return String.valueOf(Essential.vanished.contains(player));
        }
        if (params.equals("online_players")) {
            return String.valueOf(Bukkit.getServer().getOnlinePlayers().size()-Essential.vanished.size());
        }
        return super.onPlaceholderRequest(player, params);
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equals("player")) {
            return player.getName();
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