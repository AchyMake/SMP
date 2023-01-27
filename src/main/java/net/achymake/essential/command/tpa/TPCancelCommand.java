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

public class TPCancelCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (TPASettings.hasTask(player)){
                Bukkit.getScheduler().cancelTask(TPASettings.getTask(player));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You cancel tpa request"));
                Player target = TPASettings.getTPASent(player);
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getName()+"&c cancel tpa request"));
                TPASettings.removeTPARequest(player,target);
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