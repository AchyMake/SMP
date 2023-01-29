package net.achymake.essential.api;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;

public class LuckPermsSetup {
    private static boolean isLuckPermsEnabled() {
        return Bukkit.getPluginManager().getPlugin("LuckPerms") != null;
    }
    public static void setup(Essential plugin){
        if (isLuckPermsEnabled()){
        }else{
            plugin.sendMessage("You have to install 'LuckPerms'");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
