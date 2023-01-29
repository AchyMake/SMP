package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    public PlayerRespawn(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if (!PlayerConfig.get(player).getBoolean("dead"))return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Death location:"));
        Location location = new Location(Bukkit.getWorld(PlayerConfig.get(player).getString("death-location.world")),PlayerConfig.get(player).getDouble("death-location.x"),PlayerConfig.get(player).getDouble("death-location.y"),PlayerConfig.get(player).getDouble("death-location.z"),PlayerConfig.get(player).getLong("death-location.yaw"),PlayerConfig.get(player).getLong("death-location.pitch"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6World:&f "+location.getWorld().getEnvironment().name().toLowerCase()+" &6X:&f "+location.getBlockX()+" &6Y:&f "+location.getBlockY()+" &6Z:&f "+location.getBlockZ()));
        PlayerConfig.toggle(player,"dead");
    }
}