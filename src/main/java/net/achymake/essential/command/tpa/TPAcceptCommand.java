package net.achymake.essential.command.tpa;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TPAcceptCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (PlayerConfig.get(player).getKeys(false).contains("tpa-request-from")){
                    Player target = Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(player).getString("tpa-request-from")));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',"Teleporting to &f"+player.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You accepted &f"+target.getName()));
                    PlayerConfig.setLocation(target,"last-location");
                    target.teleport(player);
                    Bukkit.getScheduler().cancelTask(PlayerConfig.get(target).getInt("tpa-task"));
                    PlayerConfig.setString(target,"tpa-request-sent",null);
                    PlayerConfig.setString(target,"tpa-task",null);
                    PlayerConfig.setString(player,"tpa-request-from",null);
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
}