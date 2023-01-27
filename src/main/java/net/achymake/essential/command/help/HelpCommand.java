package net.achymake.essential.command.help;

import net.achymake.essential.files.MotdConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            if (MotdConfig.get().getKeys(false).contains("help")){
                for (String motd : MotdConfig.get().getStringList("help")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(motd,sender.getName())));
                }
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c'&fmotd.yml&c' does not contain &fhelp"));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
}