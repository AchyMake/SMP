package net.achymake.essential.command.mute;

import net.achymake.essential.settings.PlayerSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MuteCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            Player target = sender.getServer().getPlayer(args[0]);
            if (target == null){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
            }else{
                if (target == sender){
                    if (PlayerSettings.isMuted(target)){
                        PlayerSettings.toggleMute(target);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You unmuted &f"+target.getName()));
                    }else{
                        PlayerSettings.toggleMute(target);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You muted &f"+target.getName()));
                    }
                }else if (target.hasPermission("essential.mute.exempt")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to mute &f"+target.getName()));
                }else{
                    if (PlayerSettings.isMuted(target)){
                        PlayerSettings.toggleMute(target);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You unmuted &f"+target.getName()));
                    }else{
                        PlayerSettings.toggleMute(target);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You muted &f"+target.getName()));
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
            for (Player players : sender.getServer().getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
}