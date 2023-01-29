package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinVanished implements Listener {
    public PlayerJoinVanished(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinedVanished (PlayerJoinEvent event){
        if (!PlayerConfig.get(event.getPlayer()).getBoolean("vanished"))return;
        Player player = event.getPlayer();
        for (Player players : player.getServer().getOnlinePlayers()){
            players.hidePlayer(Essential.instance,player);
        }
        Essential.vanished.add(player);
        player.setAllowFlight(true);
        player.setCollidable(false);
        player.setInvulnerable(true);
        player.setCanPickupItems(false);
        player.setSleepingIgnored(true);
        player.setSilent(true);
        for (Player vanishPlayers : Essential.vanished){
            player.showPlayer(Essential.instance,vanishPlayers);
            vanishPlayers.showPlayer(Essential.instance,player);
        }
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You joined back vanished"));
        event.setJoinMessage(null);
    }
}