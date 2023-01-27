package net.achymake.essential.listeners.chat;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess implements Listener {
    public PlayerCommandPreprocess(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
        for (String disabled : Config.get().getStringList("commands.disable")){
            if (event.getMessage().toLowerCase().contains(disabled)){
                event.setCancelled(true);
            }
        }
    }
}