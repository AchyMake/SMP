package net.achymake.essential.command.mute;

import net.achymake.essential.files.MessageConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MuteCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            Player target = sender.getServer().getPlayer(args[0]);
            if (target == null){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-offline"),args[0])));
            }else{
                if (target == sender){
                    if (PlayerConfig.get(target).getBoolean("muted")){
                        PlayerConfig.toggle(target,"muted");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You unmuted &f"+target.getName()));
                    }else{
                        PlayerConfig.toggle(target,"muted");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You muted &f"+target.getName()));
                    }
                }else if (target.hasPermission("essential.mute.exempt")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to mute &f"+target.getName()));
                }else{
                    if (PlayerConfig.get(target).getBoolean("muted")){
                        PlayerConfig.toggle(target,"muted");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You unmuted &f"+target.getName()));
                    }else{
                        PlayerConfig.toggle(target,"muted");
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