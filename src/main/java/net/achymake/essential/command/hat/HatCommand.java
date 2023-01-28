package net.achymake.essential.command.hat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HatCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0){
                Player player = (Player) sender;
                if (player.getInventory().getHelmet() == null){
                    if (player.getInventory().getItemInMainHand() != null){
                        ItemStack heldItem = new ItemStack(player.getInventory().getItemInMainHand().getType());
                        heldItem.setItemMeta(player.getInventory().getItemInMainHand().getItemMeta());
                        heldItem.setAmount(1);
                        player.getInventory().setHelmet(heldItem);
                        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You are now wearing &f"+heldItem.getItemMeta().getLocalizedName()));
                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not holding any item"));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are already wearing &f"+player.getInventory().getHelmet().getItemMeta().getLocalizedName()));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
}