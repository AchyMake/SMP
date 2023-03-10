package net.achymake.essential.command.info;

import net.achymake.essential.api.EconomyProvider;
import net.achymake.essential.files.MessageConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InfoCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cusage:&f /info name"));
        } else if (args.length == 1) {
            OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayer(args[0]);
            if (PlayerConfig.exist(offlinePlayer)){
                if (offlinePlayer.isBanned()){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Banned:&f "+ offlinePlayer.isBanned()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Ban reason:&f "+ sender.getServer().getBanList(BanList.Type.NAME).getBanEntry(offlinePlayer.getName()).getReason()));
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Name:&f "+ offlinePlayer.getName()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Custom name:&f "+PlayerConfig.get(offlinePlayer).getString("custom-name")));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Balance: &a"+EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer))));
                    if (PlayerConfig.get(offlinePlayer).getConfigurationSection("homes").getKeys(false).size() > 0){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Homes:"));
                        for (String homeNames : PlayerConfig.get(offlinePlayer).getConfigurationSection("homes").getKeys(false)){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6- &f"+homeNames));
                        }
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Max homes:&a "+ PlayerConfig.get(offlinePlayer).getInt("max-homes")));
                    if (PlayerConfig.get(offlinePlayer).getBoolean("vanished")){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Vanished: &a"+PlayerConfig.get(offlinePlayer).getBoolean("vanished")));
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Vanished: &c"+PlayerConfig.get(offlinePlayer).getBoolean("vanished")));
                    }
                    if (offlinePlayer.isOnline()){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Online: &a"+ offlinePlayer.isOnline()));
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Online: &c"+ offlinePlayer.isOnline()));
                    }
                    if (offlinePlayer.isOnline()){
                        Player targetOnline = sender.getServer().getPlayerExact(args[0]);
                        World world = targetOnline.getWorld();
                        int x = (int) targetOnline.getLocation().getX();
                        int y = (int) targetOnline.getLocation().getY();
                        int z = (int) targetOnline.getLocation().getZ();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Location:"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6World: &f" + world.getName() + " &6X: &f" + x + " &6Y: &f" + y + " &6Z: &f" + z ));
                    }else{
                        String quitWorld = PlayerConfig.get(offlinePlayer).getString("quit-location.world");
                        int x = (int) PlayerConfig.get(offlinePlayer).getDouble("quit-location.x");
                        int y = (int) PlayerConfig.get(offlinePlayer).getDouble("quit-location.y");
                        int z = (int) PlayerConfig.get(offlinePlayer).getDouble("quit-location.z");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Last online:&f "+ SimpleDateFormat.getDateInstance().format(offlinePlayer.getLastPlayed())));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Quit Location:"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6World: &f" + quitWorld + " &6X: &f" + x + " &6Y: &f" + y + " &6Z: &f" + z ));
                    }
                }
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-null"),offlinePlayer.getName())));
            }

        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                commands.add(offlinePlayer.getName());
            }
            return commands;
        }
        return commands;
    }
}