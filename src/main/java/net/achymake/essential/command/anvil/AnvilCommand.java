package net.achymake.essential.command.anvil;

import net.achymake.essential.files.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class AnvilCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0){
                Player player = (Player) sender;
                Inventory inventory = player.getServer().createInventory(player, InventoryType.ANVIL);
                player.openInventory(inventory);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.get().getString("command.anvil")));
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