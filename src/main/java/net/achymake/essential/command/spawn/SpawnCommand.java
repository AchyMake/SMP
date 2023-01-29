package net.achymake.essential.command.spawn;

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

public class SpawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (spawnExist()){
                Player player = (Player) sender;
                getSpawn().getChunk().load();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting &fSpawn"));
                player.teleport(getSpawn());
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
    private boolean spawnExist(){
        return LocationConfig.get().getKeys(false).contains("spawn");
    }
    private Location getSpawn(){
        String worldName = LocationConfig.get().getString("spawn.world");
        World world = Bukkit.getWorld(worldName);
        double x = LocationConfig.get().getDouble("spawn.x");
        double y = LocationConfig.get().getDouble("spawn.y");
        double z = LocationConfig.get().getDouble("spawn.z");
        float yaw = LocationConfig.get().getLong("spawn.yaw");
        float pitch = LocationConfig.get().getLong("spawn.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
}