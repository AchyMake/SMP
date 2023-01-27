package net.achymake.essential.command.warp;

import net.achymake.essential.Essential;
import net.achymake.essential.files.LocationConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                Player player = (Player) sender;
                if (warpExist(args[0])){
                    if (args[0].equalsIgnoreCase("jail")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c does not exist"));
                    }else{
                        setLastLocation(player);
                        getWarp(args[0]).getChunk().load();
                        player.teleport(getWarp(args[0]));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting to &f"+args[0]));
                    }
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
    private void setLastLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
        config.set("last-location.world",location.getWorld().getName());
        config.set("last-location.x",location.getX());
        config.set("last-location.y",location.getY());
        config.set("last-location.z",location.getZ());
        config.set("last-location.yaw",location.getYaw());
        config.set("last-location.pitch",location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}