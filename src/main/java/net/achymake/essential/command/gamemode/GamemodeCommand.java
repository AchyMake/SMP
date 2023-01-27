package net.achymake.essential.command.gamemode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            List<String> gamemode = new ArrayList<>();
            for (GameMode values : GameMode.values()){
                gamemode.add(values.name().toLowerCase());
            }
            if (args.length == 1) {
                Player player = (Player) sender;
                if (gamemode.contains(args[0])){
                    GameMode gameMode = GameMode.valueOf(args[0].toUpperCase());
                    player.setGameMode(gameMode);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Changed game mode to&f "+gameMode.name().toLowerCase()));
                }
            }else if (args.length == 2){
                if (gamemode.contains(args[0])){
                    Player player = (Player) sender;
                    if (player.hasPermission("essential.gamemode.others")){
                        GameMode gameMode = GameMode.valueOf(args[0].toUpperCase());
                        Player target = player.getServer().getPlayerExact(args[1]);
                        if (target == null){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is offline"));
                        }else if (target.hasPermission("essential.gamemode.exempt")){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to change gamemode of &f"+target.getName()));
                        }else{
                            target.setGameMode(gameMode);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You changed &f"+target.getName()+"&6 game mode to&f "+gameMode.name().toLowerCase()));
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 changed your game mode to&f "+gameMode.name().toLowerCase()));
                        }
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (GameMode gameMode : GameMode.values()){
                commands.add(gameMode.name().toLowerCase());
            }
            return commands;
        }else if (args.length == 2){
            if (sender.hasPermission("essential.gamemode.others")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
                return commands;
            }
        }
        return commands;
    }
}