package net.achymake.essential.command.ban;

import org.bukkit.BanList;
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

public class BanCommand implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cusage:&f /ban name reason"));
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target.hasPermission("essential.ban.exempt")) {
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', target.getName() + "&6 has the reverse card!"));
            } else {
                player.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(),ChatColor.translateAlternateColorCodes('&',player.getName() + "&c banned you with no reasons"),null,null);
                Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.translateAlternateColorCodes('&', player.getName() + "&c banned you with no reasons"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou banned &f" + args[0] + "&c with no reasons"));
            }
        } else if (args.length > 2) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String words : args){
                stringBuilder.append(words);
                stringBuilder.append(" ");
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target.hasPermission("essential.ban.exempt")) {
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', target.getName() + "&6 has the reverse card!"));
            } else {
                player.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(),ChatColor.translateAlternateColorCodes('&',"&6Banned by &f" + player.getName() + "&6 reason:&f " + stringBuilder),null,null);
                Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.translateAlternateColorCodes('&', "&6Banned by &f" + player.getName() + "&6 reason:&f " + stringBuilder));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You banned &f" + args[0] + "&6 with reason &f" + stringBuilder));
            }
        }

        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                commands.add(offlinePlayer.getName());
            }
            return commands;
        }
        return commands;
    }
}
