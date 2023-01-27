package net.achymake.essential.command.kit;

import net.achymake.essential.files.KitConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitCommand implements CommandExecutor, TabCompleter {
    private final HashMap<String,Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Kits:"));
                for (String kitNames : KitConfig.get().getKeys(false)){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &f"+kitNames));
                }
            }else if (args.length == 1){
                if (player.hasPermission("essential.kit."+args[0].toLowerCase())){
                    for (String kitNames : KitConfig.get().getKeys(false)){
                        if (args[0].equals(kitNames)){
                            giveKit(player,args[0]);
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
        if (args.length == 1) {
            for (String kit : KitConfig.get().getKeys(false)){
                commands.add(kit);
            }
            return commands;
        }
        return commands;
    }
    private void giveKit(Player player, String kitName){
        if (player.hasPermission("essential.cooldown.exempt")){
            dropKit(player,kitName);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You received &f"+kitName));
        }else if (!cooldown.containsKey(player.getUniqueId()+kitName)){
            cooldown.put(player.getUniqueId()+kitName,System.currentTimeMillis());
            dropKit(player,kitName);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You received &f"+kitName));
        }else{
            Long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId()+kitName);
            String cooldownTimer = KitConfig.get().getString(kitName+".cooldown");
            Integer integer = Integer.valueOf(cooldownTimer.replace(cooldownTimer, cooldownTimer + "000"));
            if (timeElapsed > integer){
                cooldown.put(player.getUniqueId()+kitName,System.currentTimeMillis());
                dropKit(player,kitName);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You received &f"+kitName));
            }else{
                long timer = (integer-timeElapsed);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have to wait &f"+String.valueOf(timer).substring(0,String.valueOf(timer).length()-3)+"&c seconds"));
            }
        }
    }
    private void dropKit(Player player,String kitName){
        for (ItemStack items : getKit(kitName)){
            player.getWorld().dropItem(player.getLocation(),items);
        }
    }
    private List<ItemStack> getKit(String kitName){
        List<ItemStack> giveItems = new ArrayList<>();
        for (String items: KitConfig.get().getConfigurationSection(kitName+".materials").getKeys(false)){
            ItemStack item = new ItemStack(Material.valueOf(KitConfig.get().getString(kitName+".materials."+items+".type")));
            ItemMeta itemMeta = item.getItemMeta();
            if (KitConfig.get().getKeys(true).contains(kitName+".materials."+items+".display.name")){
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', KitConfig.get().getString(kitName+".materials."+items+".display.name")));
            }
            if (KitConfig.get().getKeys(true).contains(kitName+".materials."+items+".display.lore")){
                List<String> lore = new ArrayList<>();
                for (String message : KitConfig.get().getStringList(kitName+".materials."+items+".display.lore")){
                    lore.add(ChatColor.translateAlternateColorCodes('&',message));
                }
                itemMeta.setLore(lore);
            }
            if (KitConfig.get().getKeys(true).contains(kitName+".materials."+items+".enchantments")){
                for (String enchantList : KitConfig.get().getConfigurationSection(kitName+".materials."+items+".enchantments").getKeys(false)){
                    itemMeta.addEnchant(Enchantment.getByName(KitConfig.get().getString(kitName+".materials."+items+".enchantments."+enchantList+".type")), KitConfig.get().getInt(kitName+".materials."+items+".enchantments."+enchantList+".amount"),true);
                }
            }
            item.setItemMeta(itemMeta);
            item.setAmount(KitConfig.get().getInt(kitName+".materials."+items+".amount"));
            giveItems.add(item);
        }
        return giveItems;
    }
}