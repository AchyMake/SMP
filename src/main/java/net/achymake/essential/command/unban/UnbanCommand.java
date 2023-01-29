package net.achymake.essential.command.unban;

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

public class UnbanCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cusage:&f /unban name"));
        } else {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            if (player.getServer().getBannedPlayers().contains(offlinePlayer)){
                player.getServer().getBannedPlayers().remove(offlinePlayer);
                player.getServer().getBanList(BanList.Type.NAME).pardon(args[0]);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have unbanned &f" + offlinePlayer.getName()));
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c is not banned"));
            }
        }

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        for (OfflinePlayer offlinePlayer : sender.getServer().getBannedPlayers()){
            commands.add(offlinePlayer.getName());
        }
        return commands;
    }
}
