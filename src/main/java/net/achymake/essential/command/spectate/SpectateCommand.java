package net.achymake.essential.command.spectate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpectateCommand implements CommandExecutor, TabCompleter {
    private static void addPassenger(Player player, Player target){
        target.setPassenger(player);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You are now spectating &f"+target.getName()));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1) {
                Player player = (Player) sender;
                Player target = Bukkit.getPlayer(args[0]);
                if (target == player) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou should target other player"));
                } else if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                } else if (target.isEmpty()) {
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',target.getPassenger().getName()+"&c is already on "+target.getName()));
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