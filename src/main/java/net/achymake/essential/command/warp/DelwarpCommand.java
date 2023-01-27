package net.achymake.essential.command.warp;

import net.achymake.essential.files.LocationConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DelwarpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                Player player = (Player) sender;
                String warp = args[0];
                if (warp.equalsIgnoreCase("spawn")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&cYou cannot delete&f {0}&c location",warp)));
                }else if (warp.equalsIgnoreCase("jail")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&cYou cannot delete&f {0}&c location",warp)));
                }else{
                    if (warpExist(warp)){
                        delWarp(warp);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&6Warp &f{0}&6 has been deleted",warp)));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageFormat.format("&cWarp &f{0}&c does not exist",warp)));
                    }
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
            if (commands.contains("spawn")){
                commands.remove("spawn");
            }
        }
        return commands;
    }
    private boolean warpExist(String warpName){
        return LocationConfig.get().getKeys(false).contains(warpName);
    }
    private void delWarp(String warpName){
        LocationConfig.get().set(warpName,null);
        LocationConfig.save();
    }
}