package net.achymake.essential.command.ban;

import org.bukkit.*;
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
            BanList banList = player.getServer().getBanList(BanList.Type.NAME);
            OfflinePlayer offlinePlayer = player.getServer().getOfflinePlayer(args[0]);
            banList.addBan(offlinePlayer.getName(),ChatColor.translateAlternateColorCodes('&', "&cno reasons"),null,null);
            banList.getBanEntry(offlinePlayer.getName()).save();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou banned &f"+offlinePlayer.getName()+"&c with no reasons"));
        } else if (args.length > 2) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++){
                stringBuilder.append(args[i]);
                stringBuilder.append(" ");
            }
            BanList banList = player.getServer().getBanList(BanList.Type.NAME);
            OfflinePlayer offlinePlayer = player.getServer().getOfflinePlayer(args[0]);
            banList.addBan(offlinePlayer.getName(),ChatColor.translateAlternateColorCodes('&', stringBuilder.toString()),null,null);
            banList.getBanEntry(offlinePlayer.getName()).save();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou banned &f"+offlinePlayer.getName()+"&c for &f"+stringBuilder));
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
