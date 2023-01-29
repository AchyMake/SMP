package net.achymake.essential.command.pay;

import net.achymake.essential.files.MessageConfig;
import net.achymake.essential.files.PlayerConfig;
import net.achymake.essential.api.EconomyProvider;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PayCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                OfflinePlayer offlinePlayer = player.getServer().getOfflinePlayer(args[0]);
                if (PlayerConfig.exist(offlinePlayer)){
                    if (Integer.parseInt(args[1]) <= EconomyProvider.getEconomy(player)){
                        EconomyProvider.addEconomy(offlinePlayer, Double.parseDouble(args[1]));
                        EconomyProvider.removeEconomy(player, Double.valueOf(args[1]));
                        if (offlinePlayer.isOnline()){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You paid &f"+offlinePlayer.getName()+" &c"+ EconomyProvider.getFormat(Double.valueOf(args[1]))));
                            player.getServer().getPlayerExact(args[0]).sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 paid you &a"+ EconomyProvider.getFormat(Double.valueOf(args[1]))));
                        }else{
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You paid &f"+offlinePlayer.getName()+" &c"+ EconomyProvider.getFormat(Double.valueOf(args[1]))));
                        }
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou dont have enough"));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-null"),offlinePlayer.getName())));
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
        }else if (args.length == 2) {
            commands.add("50");
            commands.add("100");
            commands.add("1000");
            return commands;
        }
        return commands;
    }
}