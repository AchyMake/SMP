package net.achymake.essential.command.give;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage:&f /give item amount"));
        }else if (args.length == 1){
            ItemStack itemStack = new ItemStack(Material.valueOf(args[0].toUpperCase()));
            itemStack.setAmount(1);
            player.getInventory().addItem(itemStack);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Added &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()+"&6 to inventory"));
        } else if (args.length == 2) {
            ItemStack itemStack = new ItemStack(Material.valueOf(args[0].toUpperCase()));
            itemStack.setAmount(Integer.parseInt(args[1]));
            player.getInventory().addItem(itemStack);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Added &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()+"&6 to inventory"));
        } else if (args.length == 3) {
            ItemStack itemStack = new ItemStack(Material.valueOf(args[0].toUpperCase()));
            itemStack.setAmount(Integer.parseInt(args[1]));
            if (args[2].equalsIgnoreCase("all")){
                for (Player players : Bukkit.getOnlinePlayers()){
                    player.getLocation().getWorld().dropItem(players.getLocation(),itemStack);
                    players.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 gave &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()+"&6 to everyone"));
                }
            }else{
                Player target = Bukkit.getPlayerExact(args[2]);
                if (target == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                }else{
                    target.getInventory().addItem(itemStack);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You gave &f"+target.getName()+" "+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 gave you &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Material material : Material.values()){
                commands.add(material.toString().toLowerCase());
            }
            return commands;
        } else if (args.length == 2) {
            commands.add("1");
            commands.add( "32");
            commands.add( "64");
            return commands;
        } else if (args.length == 3) {
            for (Player players : Bukkit.getOnlinePlayers()){
                commands.add(players.getName());
            }
            commands.add("all");
            return commands;
        }
        return commands;
    }
}