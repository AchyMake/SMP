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

public class SethomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (PlayerConfig.get(player).getKeys(false).contains("homes")){
                    if (PlayerConfig.get(player).getKeys(true).contains("homes.home")){
                        setHomeLocation(player,"home");
                    }else if (PlayerConfig.get(player).getInt("max-homes") > PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).size()){
                        setHomeLocation(player,"home");
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have reach your limit"));
                    }
                }else{
                    setHomeLocation(player,"home");
                }
            }else if (args.length == 1){
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("bed")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou cant set home for &f"+args[0]));
                }else if (args[0].equalsIgnoreCase("buy")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou cant set home for &f"+args[0]));
                }else{
                    if (PlayerConfig.get(player).getKeys(false).contains("homes")){
                        if (PlayerConfig.get(player).getKeys(true).contains("homes."+args[0])){
                            setHomeLocation(player,args[0]);
                        }else if (PlayerConfig.get(player).getInt("max-homes") > PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).size()){
                            setHomeLocation(player,args[0]);
                        }else{
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have reach your limit"));
                        }
                    }else{
                        setHomeLocation(player,args[0]);
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
            if (PlayerConfig.get(player).getKeys(false).contains("homes")){
                for (String home : PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false)){
                    commands.add(home);
                }
                return commands;
            }
        }
        return commands;
    }
    private void setHomeLocation(Player player,String home){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("homes."+home+".world",player.getLocation().getWorld().getName());
        config.set("homes."+home+".x",player.getLocation().getX());
        config.set("homes."+home+".y",player.getLocation().getY());
        config.set("homes."+home+".z",player.getLocation().getZ());
        config.set("homes."+home+".yaw",player.getLocation().getYaw());
        config.set("homes."+home+".pitch",player.getLocation().getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home &f"+home+"&6 has been set"));
    }
}