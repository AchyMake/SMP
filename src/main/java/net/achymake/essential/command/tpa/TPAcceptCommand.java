package net.achymake.essential.command.tpa;

import net.achymake.essential.settings.PlayerSettings;
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

public class TPAcceptCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (TPASettings.hasTPARequest(player)){
                    Player target = TPASettings.getTPAFrom(player);
                    Bukkit.getScheduler().cancelTask(TPASettings.getTask(target));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',"Teleporting to &f"+player.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You accepted &f"+target.getName()));
                    PlayerSettings.setLastLocation(target);
                    target.teleport(player);
                    TPASettings.removeTPARequest(target,player);
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou dont have any tpa request"));
                }
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