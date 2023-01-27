package net.achymake.essential;

import net.achymake.essential.api.VaultSetup;
import net.achymake.essential.command.Commands;
import net.achymake.essential.files.Files;
import net.achymake.essential.listeners.Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Essential extends JavaPlugin {
    public static List<Player> vanished = new ArrayList<>();
    public static Essential instance;
    @Override
    public void onEnable(){
        VaultSetup.setup(this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        instance = this;
        Files.start();
        Events.start(this);
        Commands.start(this);
        sendMessage("&aEnabled &f"+this.getName()+ " " +this.getDescription().getVersion());
    }
    @Override
    public void onDisable(){
        vanished.clear();
        sendMessage("&cDisabled &f"+this.getName()+ " " +this.getDescription().getVersion());
    }
    public void sendMessage(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l[&e"+this.getName()+"&6&l]&r "+message));
    }
}