package net.achymake.essential.command.pay;

import net.achymake.essential.settings.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PayCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage: &f/pay player amount"));
            }else if (args.length == 1){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage: &f/pay player amount"));
            }else if (args.length == 2) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if (Integer.parseInt(args[1]) <= Economy.getEconomy(player)){
                    Economy.addEconomy(target, Double.parseDouble(args[1]));
                    Economy.removeEconomy(player, Double.valueOf(args[1]));
                    if (target.isOnline()){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You paid &f"+target.getName()+" &c$"+ Economy.getFormat(Double.valueOf(args[1]))));
                        Bukkit.getPlayerExact(args[0]).sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 paid you &a$"+ Economy.getFormat(Double.valueOf(args[1]))));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You paid &f"+target.getName()+" &c$"+ Economy.getFormat(Double.valueOf(args[1]))));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou dont have enough"));
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
        }else if (args.length == 2) {
            commands.add("50");
            commands.add("100");
            commands.add("1000");
            return commands;
        }
        return commands;
    }
}