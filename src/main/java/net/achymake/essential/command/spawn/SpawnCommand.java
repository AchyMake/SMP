package net.achymake.essential.command.spawn;

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

public class SpawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (spawnExist()){
                Player player = (Player) sender;
                setLastLocation(player);
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