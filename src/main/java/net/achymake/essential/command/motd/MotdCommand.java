package net.achymake.essential.command.motd;

import net.achymake.essential.files.MotdConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MotdCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            for (String motd : MotdConfig.get().getStringList("motd")){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(motd,sender.getName())));
            }
        }else if (args.length == 1){
            if (MotdConfig.get().getKeys(false).contains(args[0])){
                for (String motd : MotdConfig.get().getStringList(args[0])){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(motd,sender.getName())));
                }
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c'&fmotd.yml&c' does not contain &f"+args[0]));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            for (String motd : MotdConfig.get().getKeys(false)){
                commands.add(motd);
            }
            return commands;
        }
        return commands;
    }
}