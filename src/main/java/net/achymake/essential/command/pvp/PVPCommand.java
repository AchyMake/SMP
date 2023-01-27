package net.achymake.essential.command.pvp;

import net.achymake.essential.settings.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PVPCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                PlayerSettings.togglePVP(player);
            } else if (args.length == 1) {
                if (player.hasPermission("essential.pvp.others")){
                    Player target = player.getServer().getPlayerExact(args[0]);
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                    }else if (target.hasPermission("essential.pvp.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to change pvp of "+target.getName()));
                    }else{
                        PlayerSettings.togglePVP(target);
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
            if (sender.hasPermission("essential.pvp.others")){
                for (Player players : Bukkit.getOnlinePlayers()){
                    commands.add(players.getName());
                }
                return commands;
            }
        }
        return commands;
    }
}