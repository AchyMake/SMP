package net.achymake.essential.command.homes;

import net.achymake.essential.command.homes.sub.Delete;
import net.achymake.essential.command.homes.sub.Teleport;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomesCommand implements CommandExecutor, TabCompleter {
    public ArrayList<HomesSubCommand> homesSubCommands = new ArrayList<>();
    public HomesCommand(){
        homesSubCommands.add(new Delete());
        homesSubCommands.add(new Teleport());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).size() > 0){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Homes:"));
                    if (player.hasPermission("homes.bed")){
                        if (player.getBedSpawnLocation() != null){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7-&f bed"));
                        }
                    }
                    for (String listedHomes : PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false)){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7-&f "+listedHomes));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You havent set any homes yet"));
                }
            }else{
                for (HomesSubCommand commands : homesSubCommands){
                    if (args[0].equals(commands.getName())){
                        commands.perform((Player) sender,args);
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("essential.homes.delete")){
                commands.add("delete");
            }
            if (sender.hasPermission("essential.homes.teleport")){
                commands.add("teleport");
            }
            return commands;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("teleport")){
                for (OfflinePlayer offlinePlayers : sender.getServer().getOfflinePlayers()){
                    commands.add(Bukkit.getOfflinePlayer(offlinePlayers.getUniqueId()).getName());
                }
                return commands;
            } else if (args[0].equalsIgnoreCase("delete")){
                for (OfflinePlayer offlinePlayers : sender.getServer().getOfflinePlayers()){
                    commands.add(Bukkit.getOfflinePlayer(offlinePlayers.getUniqueId()).getName());
                }
                return commands;
            }
        } else if (args.length == 3) {
            if (sender.hasPermission("essential.homes.teleport")){
                OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayer(args[1]);
                for (String home : PlayerConfig.get(offlinePlayer).getConfigurationSection("homes").getKeys(false)){
                    commands.add(home);
                }
            }
        }
        return commands;
    }
}