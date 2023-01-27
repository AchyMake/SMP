package net.achymake.essential.command.spawn;

import net.achymake.essential.files.LocationConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetspawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                setSpawn(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Spawn set"));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
    private void setSpawn(Player player){
        Location location = player.getLocation();
        LocationConfig.get().set("spawn.world",location.getWorld().getName());
        LocationConfig.get().set("spawn.x",location.getX());
        LocationConfig.get().set("spawn.y",location.getY());
        LocationConfig.get().set("spawn.z",location.getZ());
        LocationConfig.get().set("spawn.yaw",location.getYaw());
        LocationConfig.get().set("spawn.pitch",location.getPitch());
        LocationConfig.save();
    }
}