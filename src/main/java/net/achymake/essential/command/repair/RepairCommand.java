package net.achymake.essential.command.repair;

import net.achymake.essential.files.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RepairCommand implements CommandExecutor, TabCompleter {
    private static final HashMap<UUID, Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou have to hold an item"));
                }else{
                    short damageReset = 0;
                    if (player.hasPermission("essential.cooldown.exempt")){
                        player.getInventory().getItemInMainHand().setDurability(damageReset);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You repaired &f"+player.getInventory().getItemInMainHand().getType().toString().toLowerCase()));
                    }else if (!cooldown.containsKey(player.getUniqueId())){
                        cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                        player.getInventory().getItemInMainHand().setDurability(damageReset);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You repaired &f"+player.getInventory().getItemInMainHand().getType().toString().toLowerCase()));
                    }else{
                        Long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                        String cooldownTimer = Config.get().getString("commands.cooldown.repair");
                        Integer integer = Integer.valueOf(cooldownTimer.replace(cooldownTimer, cooldownTimer + "000"));
                        if (timeElapsed > integer){
                            cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                            player.getInventory().getItemInMainHand().setDurability(damageReset);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You repaired &f"+player.getInventory().getItemInMainHand().getType().toString().toLowerCase()));
                        }else{
                            long timer = (integer-timeElapsed);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have to wait &f"+String.valueOf(timer).substring(0,String.valueOf(timer).length()-3)+"&c seconds"));
                        }
                    }
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