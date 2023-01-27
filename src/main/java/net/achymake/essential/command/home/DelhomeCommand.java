package net.achymake.essential.command.home;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DelhomeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 1){
                if (PlayerConfig.get(player).getKeys(true).contains("homes."+args[0])){
                    File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
                    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                    config.set("homes."+args[0],null);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        Essential.instance.sendMessage(e.getMessage());
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+args[0]+"&6 deleted"));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c does not exist"));
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
            if (PlayerConfig.get(player).getKeys(true).contains("homes")){
                for (String home : PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false)){
                    commands.add(home);
                }
                return commands;
            }
        }
        return commands;
    }
}