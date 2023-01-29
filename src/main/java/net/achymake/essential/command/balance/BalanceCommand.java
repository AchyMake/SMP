package net.achymake.essential.command.balance;

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

public class BalanceCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Balance: &a"+ EconomyProvider.getFormat(EconomyProvider.getEconomy(player))));
        } else if (args.length == 1) {
            if (player.hasPermission("essential.balance.others")){
                OfflinePlayer offlinePlayer = player.getServer().getOfflinePlayer(args[0]);
                if (PlayerConfig.exist(offlinePlayer)){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+" &6balance: &a"+ EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer))));
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
        if (args.length == 1) {
            if (sender.hasPermission("essential.balance.others")) {
                for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()) {
                    commands.add(offlinePlayer.getName());
                }
                return commands;
            }
        }
        return commands;
    }
}