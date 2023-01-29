package net.achymake.essential.command.home;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SethomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                String homeName = "home";
                if (PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).contains(homeName)){
                    PlayerConfig.setLocation(player,"homes."+homeName);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+homeName+"&6 has been set"));
                }else if (PlayerConfig.get(player).getInt("max-homes") > PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).size()){
                    PlayerConfig.setLocation(player,"homes."+homeName);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+homeName+"&6 has been set"));
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have reach your limit"));
                }
            }else if (args.length == 1){
                Player player = (Player) sender;
                String homeName = args[0];
                if (homeName.equalsIgnoreCase("bed")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou cant set home for &f"+homeName));
                }else if (homeName.equalsIgnoreCase("buy")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou cant set home for &f"+homeName));
                }else{
                    if (PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).contains(homeName)){
                        PlayerConfig.setLocation(player,"homes."+homeName);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+homeName+"&6 has been set"));
                    }else if (PlayerConfig.get(player).getInt("max-homes") > PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).size()){
                        PlayerConfig.setLocation(player,"homes."+homeName);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+homeName+"&6 has been set"));
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have reach your limit"));
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        Player player = (Player) sender;
        if (args.length == 1) {
            for (String home : PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false)){
                commands.add(home);
            }
        }
        return commands;
    }
}