package net.achymake.essential.command.kick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KickCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage:&f /kick name reason"));
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
            }else if (target.hasPermission("essential.kick.exempt")){
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&',target.getName()+"&6 has the reverse card!"));
            }else{
                Bukkit.getPlayerExact(target.getName()).kickPlayer(ChatColor.translateAlternateColorCodes('&',"&6Kicked by &f"+player.getName()+"&6 reason:&f None"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You kicked &f"+args[0]+"&6 with reason &fNone"));
            }
        } else if (args.length > 2) {
            Player target = Bukkit.getPlayer(args[0]);
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < args.length; i++){
                stringBuilder.append(args[i]);
                stringBuilder.append(" ");
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You kicked &f"+args[0]+"&6 with reason &f"+stringBuilder));
            Bukkit.getPlayerExact(target.getName()).kickPlayer(ChatColor.translateAlternateColorCodes('&',"&6Kicked by &f"+player.getName()+"&6 reason:&f "+stringBuilder));
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