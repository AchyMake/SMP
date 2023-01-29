package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.LocationConfig;
import net.achymake.essential.files.MotdConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.MessageFormat;

public class PlayerJoinSetup implements Listener {
    public PlayerJoinSetup(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinSetup (PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (PlayerConfig.get(player).getBoolean("new")){
            if (LocationConfig.locationExist("spawn")){
                LocationConfig.getLocation("spawn").getChunk().load();
                player.teleport(LocationConfig.getLocation("spawn"));
                for (Player players : player.getServer().getOnlinePlayers()){
                    for (String welcome : MotdConfig.get().getStringList("welcome")){
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(welcome,player.getName())));
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"Spawn &chas not been set"));
            }
            PlayerConfig.toggle(player,"new");
        }else{
            for (String welcomeBack : MotdConfig.get().getStringList("welcome-back")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(welcomeBack,event.getPlayer().getName())));
            }
        }
    }
}