package net.achymake.essential.command.tpa;

import net.achymake.essential.Essential;
import net.achymake.essential.settings.TPASettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TPAcceptCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (TPASettings.hasTPARequest(player)){
                    Player target = TPASettings.getTPAFrom(player);
                    Bukkit.getScheduler().cancelTask(TPASettings.getTask(target));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',"Teleporting to &f"+player.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You accepted &f"+target.getName()));
                    setLastLocation(target);
                    target.teleport(player);
                    TPASettings.removeTPARequest(target,player);
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou dont have any tpa request"));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
    private void setLastLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
        config.set("last-location.world",location.getWorld().getName());
        config.set("last-location.x",location.getX());
        config.set("last-location.y",location.getY());
        config.set("last-location.z",location.getZ());
        config.set("last-location.yaw",location.getYaw());
        config.set("last-location.pitch",location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}