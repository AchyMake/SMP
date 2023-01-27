package net.achymake.essential.command.tpa;

import net.achymake.essential.settings.TPASettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPDenyCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (TPASettings.hasTPARequest(player)){
                Player target = TPASettings.getTPAFrom(player);
                Bukkit.getScheduler().cancelTask(TPASettings.getTask(target));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&c denied your tpa request"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You denied tpa request from &f"+target.getName()));
                TPASettings.removeTPARequest(target,player);
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
}