package net.achymake.essential.command.warp;

import net.achymake.essential.files.LocationConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                Player player = (Player) sender;
                if (warpExist(args[0])){
                    getWarp(args[0]).getChunk().load();
                    player.teleport(getWarp(args[0]));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting to &f"+args[0]));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c does not exist"));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            for (String locations : LocationConfig.get().getKeys(false)){
                commands.add(locations);
            }
            if (commands.contains("jail")){
                commands.remove("jail");
            }
            return commands;
        }
        return commands;
    }
    private boolean warpExist(String warpName){
        return LocationConfig.get().getKeys(false).contains(warpName);
    }
    private Location getWarp(String warpName){
        String worldName = LocationConfig.get().getString(warpName+".world");
        World world = Bukkit.getWorld(worldName);
        double x = LocationConfig.get().getDouble(warpName+".x");
        double y = LocationConfig.get().getDouble(warpName+".y");
        double z = LocationConfig.get().getDouble(warpName+".z");
        float yaw = LocationConfig.get().getLong(warpName+".yaw");
        float pitch = LocationConfig.get().getLong(warpName+".pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
}