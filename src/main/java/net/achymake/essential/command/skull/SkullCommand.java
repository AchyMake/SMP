package net.achymake.essential.command.skull;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1) {
                Player player = (Player) sender;
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                if (offlinePlayer != null){
                    ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
                    skullMeta.setOwningPlayer(offlinePlayer);
                    skullItem.setItemMeta(skullMeta);
                    player.getInventory().addItem(skullItem);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Gave skull of &f"+offlinePlayer.getName()));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Player players : Bukkit.getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
}