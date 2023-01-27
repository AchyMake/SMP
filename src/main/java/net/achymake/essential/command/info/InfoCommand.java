package net.achymake.essential.command.info;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InfoCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cusage:&f /info name"));
        } else if (args.length == 1) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            if (PlayerConfig.exist(offlinePlayer)){
                if (PlayerConfig.get(offlinePlayer).getBoolean("banned")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Banned:&f "+ PlayerConfig.get(offlinePlayer).getBoolean("banned")));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Ban reason:&f "+ PlayerConfig.get(offlinePlayer).getString("banned-reason")));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Name:&f "+ PlayerConfig.get(offlinePlayer).getString("name")));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Online:&f "+ offlinePlayer.isOnline()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Max homes:&f "+ PlayerConfig.get(offlinePlayer).getInt("max-homes")));
                    if (offlinePlayer.isOnline()){
                        Player targetOnline = Bukkit.getPlayer(args[0]);
                        World world = targetOnline.getWorld();
                        int x = (int) targetOnline.getLocation().getX();
                        int y = (int) targetOnline.getLocation().getY();
                        int z = (int) targetOnline.getLocation().getZ();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Location:"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6World: &f" + world.getName() + " &6X: &f" + x + " &6Y: &f" + y + " &6Z: &f" + z ));
                    }else{
                        World quitWorld = Bukkit.getWorld(PlayerConfig.get(offlinePlayer).getString("quit-location.world"));
                        int x = (int) PlayerConfig.get(offlinePlayer).getDouble("quit-location.x");
                        int y = (int) PlayerConfig.get(offlinePlayer).getDouble("quit-location.y");
                        int z = (int) PlayerConfig.get(offlinePlayer).getDouble("quit-location.z");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Last online:&f "+ SimpleDateFormat.getDateInstance().format(offlinePlayer.getLastPlayed())));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Quit Location:"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6World: &f" + quitWorld.getName() + " &6X: &f" + x + " &6Y: &f" + y + " &6Z: &f" + z ));
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c has never joined the server"));
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