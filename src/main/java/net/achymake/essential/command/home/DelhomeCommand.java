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

public class DelhomeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                Player player = (Player) sender;
                String homeName = args[0];
                if (PlayerConfig.get(player).getKeys(false).contains("homes."+homeName)){
                    PlayerConfig.setString(player,"homes."+homeName,null);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+homeName+"&6 deleted"));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',homeName+"&c does not exist"));
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