package net.achymake.essential.command.announcement;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String words : args){
                stringBuilder.append(words);
                stringBuilder.append(" ");
            }
            for (Player players : sender.getServer().getOnlinePlayers()){
                players.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6[&eServer&6]&r "+stringBuilder));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> empty = new ArrayList<>();
        return empty;
    }
}