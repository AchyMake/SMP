package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.settings.VanishSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitVanished implements Listener {
    public PlayerQuitVanished(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuitWithTPATask (PlayerQuitEvent event){
        if (!VanishSettings.isVanished(event.getPlayer()))return;
        toggleVanish(event.getPlayer());
    }
    private void toggleVanish(Player player){
        Essential.vanished.remove(player);
        player.setAllowFlight(false);
        player.setCollidable(true);
        player.setInvulnerable(false);
        player.setCanPickupItems(true);
        player.setSleepingIgnored(false);
        player.setSilent(false);
    }
}