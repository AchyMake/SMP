package net.achymake.essential.command.enchant;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EnchantCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage:&f /enchant enchantment level"));
        }else if (args.length == 1){
            if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have to hold an item"));
            }else{
                if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.getByName(args[0].toUpperCase()))){
                    player.getInventory().getItemInMainHand().removeEnchantment(Enchantment.getByName(args[0].toUpperCase()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You removed &f"+args[0]));
                }else{
                    player.getInventory().getItemInMainHand().addEnchantment(Enchantment.getByName(args[0]),1);
                }
            }
        }else if (args.length == 2){
            if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have to hold an item"));
            }else{
                if (Integer.valueOf(args[1]) > 0){
                    player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.getByName(args[0].toUpperCase()),Integer.valueOf(args[1]));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You enchanted item with &f"+Enchantment.getByName(args[0].toUpperCase()).getName().toLowerCase()+"&6 lvl &f"+args[1]));
                }else{
                    player.getInventory().getItemInMainHand().removeEnchantment(Enchantment.getByName(args[0].toUpperCase()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You removed &f"+args[0]));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Enchantment enchantment : Enchantment.values()){
                commands.add(enchantment.getName().toLowerCase());
            }
            return commands;
        } else if (args.length == 2) {
            commands.add(String.valueOf(Enchantment.getByName(args[0].toUpperCase()).getMaxLevel()));
            return commands;
        }
        return commands;
    }
}