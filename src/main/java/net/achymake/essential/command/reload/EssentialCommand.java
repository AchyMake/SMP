package net.achymake.essential.command.reload;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Files;
import net.achymake.essential.tablist.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class EssentialCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage:&f /ess reload"));
        }else if (args.length == 1){
            if (args[0].equalsIgnoreCase("reload")){
                Files.reload();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Essential reloaded"));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        commands.add("reload");
        return commands;
    }
}