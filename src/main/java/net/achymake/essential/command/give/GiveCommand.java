package net.achymake.essential.command.give;

import net.achymake.essential.files.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class GiveCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage:&f /give item amount"));
            }else if (args.length == 1){
                Player player = (Player) sender;
                ItemStack itemStack = new ItemStack(Material.valueOf(args[0].toUpperCase()));
                itemStack.setAmount(1);
                player.getInventory().addItem(itemStack);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Added &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()+"&6 to inventory"));
            } else if (args.length == 2) {
                Player player = (Player) sender;
                ItemStack itemStack = new ItemStack(Material.valueOf(args[0].toUpperCase()));
                itemStack.setAmount(Integer.parseInt(args[1]));
                player.getInventory().addItem(itemStack);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Added &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()+"&6 to inventory"));
            } else if (args.length == 3) {
                Player player = (Player) sender;
                ItemStack itemStack = new ItemStack(Material.valueOf(args[0].toUpperCase()));
                itemStack.setAmount(Integer.parseInt(args[1]));
                if (args[2].equalsIgnoreCase("all")){
                    for (Player players : player.getServer().getOnlinePlayers()){
                        player.getLocation().getWorld().dropItem(players.getLocation(),itemStack);
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 gave &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()+"&6 to everyone"));
                    }
                }else{
                    Player target = player.getServer().getPlayerExact(args[2]);
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-offline"),args[0])));
                    }else{
                        target.getInventory().addItem(itemStack);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You gave &f"+target.getName()+" "+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 gave you &f"+itemStack.getAmount()+" "+itemStack.getType().toString().toLowerCase()));
                    }
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
            for (Player players : sender.getServer().getOnlinePlayers()){
                commands.add(players.getName());
            }
            commands.add("all");
            return commands;
        }
        return commands;
    }
}