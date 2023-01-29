package net.achymake.essential.api;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;

public class PlaceholderSetup {
    private static boolean isPlaceholderAPIEnabled() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }
    public static void setup(Essential plugin){
        if (isPlaceholderAPIEnabled()){
            new PlaceholderProvider().register();
        }else{
            plugin.sendMessage("You have to install 'PlaceholderAPI'");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}