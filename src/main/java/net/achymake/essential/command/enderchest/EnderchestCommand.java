package net.achymake.essential.command.enderchest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EnderchestCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                player.openInventory(player.getEnderChest());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Opened&f Enderchest"));
            } else if (args.length == 1) {
                Player player = (Player) sender;
                if (player.hasPermission("essential.enderchest.others")){
                    Player target = player.getServer().getPlayerExact(args[0]);
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is offline"));
                    }else{
                        if (target == player){
                            player.openInventory(target.getEnderChest());
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Opened &fEnderchest&6 of &f"+target.getName()));
                        }else if (target.hasPermission("essential.enderchest.exempt")){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to open &f"+target.getName()+"&c enderchest"));
                        }else{
                            player.openInventory(target.getEnderChest());
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Opened &fEnderchest&6 of &f"+target.getName()));
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
            if (sender.hasPermission("essential.enderchest.others")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
                return commands;
            }
        }
        return commands;
    }
}