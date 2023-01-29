package net.achymake.essential.command.vanish;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (PlayerConfig.get(player).getBoolean("vanished")){
                    for (Player players : player.getServer().getOnlinePlayers()){
                        players.showPlayer(Essential.instance,player);
                    }
                    Essential.vanished.remove(player);
                    PlayerConfig.toggle(player,"vanished");
                    for (Player vanishedPlayers : Essential.vanished){
                        player.hidePlayer(Essential.instance,vanishedPlayers);
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You are no longer vanished"));
                }else{
                    for (Player players : player.getServer().getOnlinePlayers()){
                        players.hidePlayer(Essential.instance,player);
                    }
                    Essential.vanished.add(player);
                    PlayerConfig.toggle(player,"vanished");
                    for (Player vanishedPlayers : Essential.vanished){
                        vanishedPlayers.showPlayer(Essential.instance,player);
                        player.showPlayer(Essential.instance,vanishedPlayers);
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You are now vanished"));
                }
            }else if (args.length == 1){
                OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayer(args[0]);
                if (offlinePlayer.isOnline()){
                    Player target = sender.getServer().getPlayerExact(args[0]);
                    if (PlayerConfig.get(offlinePlayer).getBoolean("vanished")){
                        for (Player players : sender.getServer().getOnlinePlayers()){
                            players.showPlayer(Essential.instance,target);
                        }
                        Essential.vanished.remove(target);
                        PlayerConfig.toggle(offlinePlayer,"vanished");
                        for (Player vanishedPlayers : Essential.vanished){
                            target.hidePlayer(Essential.instance,vanishedPlayers);
                        }
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&6 are no longer vanished"));
                    }else{
                        for (Player players : sender.getServer().getOnlinePlayers()){
                            players.hidePlayer(Essential.instance,target);
                        }
                        Essential.vanished.add(target);
                        PlayerConfig.toggle(offlinePlayer,"vanished");
                        for (Player vanishedPlayers : Essential.vanished){
                            vanishedPlayers.showPlayer(Essential.instance,target);
                            target.showPlayer(Essential.instance,vanishedPlayers);
                        }
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&6 are now vanished"));
                    }
                }else{
                    if (PlayerConfig.exist(offlinePlayer)){
                        if (PlayerConfig.get(offlinePlayer).getBoolean("vanished")){
                            PlayerConfig.toggle(offlinePlayer,"vanished");
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&6 are no longer vanished"));
                        }else{
                            PlayerConfig.toggle(offlinePlayer,"vanished");
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&6 are now vanished"));
                        }
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c has never joined"));
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
            for (Player players : sender.getServer().getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
}