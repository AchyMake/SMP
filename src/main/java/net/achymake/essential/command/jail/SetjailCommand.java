package net.achymake.essential.command.jail;

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

public class SetjailCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                setJail(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Jail set"));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> empty = new ArrayList<>();
        return empty;
    }
    private void setJail(Player player){
        Location location = player.getLocation();
        LocationConfig.get().set("jail.world",location.getWorld().getName());
        LocationConfig.get().set("jail.x",location.getX());
        LocationConfig.get().set("jail.y",location.getY());
        LocationConfig.get().set("jail.z",location.getZ());
        LocationConfig.get().set("jail.yaw",location.getYaw());
        LocationConfig.get().set("jail.pitch",location.getPitch());
        LocationConfig.save();
    }
}