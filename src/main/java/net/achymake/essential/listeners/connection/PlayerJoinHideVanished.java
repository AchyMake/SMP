package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHideVanished implements Listener {
    public PlayerJoinHideVanished(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinHideVanished (PlayerJoinEvent event){
        if (PlayerConfig.get(event.getPlayer()).getBoolean("vanished"))return;
        for (Player vanishedPlayers : Essential.vanished){
            event.getPlayer().hidePlayer(Essential.instance,vanishedPlayers);
        }
    }
}