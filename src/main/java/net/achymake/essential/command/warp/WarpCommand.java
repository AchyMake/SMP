package net.achymake.essential.command.warp;

import net.achymake.essential.files.LocationConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                Player player = (Player) sender;
                String warpName = args[0];
                if (LocationConfig.locationExist(warpName)){
                    if (warpName.equalsIgnoreCase("jail")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',warpName+"&c does not exist"));
                    }else{
                        PlayerConfig.setLocation(player,"last-location");
                        LocationConfig.getLocation(warpName).getChunk().load();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting "+warpName));
                        player.teleport(LocationConfig.getLocation(warpName));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',warpName+"&c does not exist"));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            for (String locations : LocationConfig.get().getKeys(false)){
                commands.add(locations);
            }
            if (commands.contains("jail")){
                commands.remove("jail");
            }
            return commands;
        }
        return commands;
    }
}