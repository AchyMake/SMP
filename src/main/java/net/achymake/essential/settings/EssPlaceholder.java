package net.achymake.essential.settings;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EssPlaceholder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "essential";
    }
    @Override
    public String getAuthor() {
        return "achy";
    }
    @Override
    public String getVersion() {
        return "1.10.0";
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
        if (player != null){
            if (params.equals("suffix")){
                return ChatColor.translateAlternateColorCodes('&',PlayerConfig.get(player).getString("suffix"));
            }
            if (params.equals("prefix")){
                return ChatColor.translateAlternateColorCodes('&',PlayerConfig.get(player).getString("prefix"));
            }
            if (params.equals("player")){
                return player.getName();
            }
        }else{
            return "";
        }
        return null;
    }
}