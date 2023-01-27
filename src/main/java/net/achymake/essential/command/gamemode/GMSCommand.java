package net.achymake.essential.command.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GMSCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Changed game mode to&f Survival"));
            }else{
                if (player.hasPermission("essential.gamemode.others")){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                    }else if (target.hasPermission("essential.gamemode.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to change gamemode of &f"+target.getName()));
                    }else{
                        target.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You changed &f"+target.getName()+"&6 game mode to&f Survival"));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 changed your game mode to&f Survival"));
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (sender.hasPermission("essential.gamemode.others")){
            if (args.length == 1){
                for (Player players : Bukkit.getOnlinePlayers()){
                    commands.add(players.getName());
                }
                return commands;
            }
        }
        return commands;
    }
}