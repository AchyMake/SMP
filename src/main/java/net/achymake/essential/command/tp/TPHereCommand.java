package net.achymake.essential.command.tp;

import net.achymake.essential.settings.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPHereCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                Player player = (Player) sender;
                Player target = sender.getServer().getPlayerExact(args[0]);
                if (target == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                }else{
                    PlayerSettings.setLastLocation(target);
                    target.teleport(player.getLocation());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You teleported &f"+target.getName()+"&6 to you"));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting to &f"+player.getName()));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Player players : Bukkit.getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
}