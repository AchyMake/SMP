package net.achymake.essential.command.spawn;

import net.achymake.essential.files.LocationConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (LocationConfig.locationExist("spawn")){
                Player player = (Player) sender;
                LocationConfig.getLocation("spawn").getChunk().load();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting &fSpawn"));
                player.teleport(LocationConfig.getLocation("spawn"));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSpawn has not been set"));
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