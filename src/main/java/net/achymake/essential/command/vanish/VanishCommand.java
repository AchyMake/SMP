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
                    Essential.vanished.remove(player);
                    PlayerConfig.toggle(player,"vanished");
                }else{
                    Essential.vanished.add(player);
                    PlayerConfig.toggle(player,"vanished");
                }
            }else if (args.length == 1){
                OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayer(args[0]);
                if (PlayerConfig.exist(offlinePlayer)){
                    if (PlayerConfig.get(offlinePlayer).getBoolean("vanished")){
                        PlayerConfig.toggle(offlinePlayer,"vanished");
                    }else{
                        PlayerConfig.toggle(offlinePlayer,"vanished");
                    }
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c has never joined"));
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