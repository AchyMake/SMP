package net.achymake.essential.command.back;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import net.achymake.essential.settings.PlayerSettings;
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

public class BackCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("essential.back.death")) {
                if (hasDeathLocation(player)){
                    setLastLocation(player);
                    getDeathLocation(player).getChunk().load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting to&f death Location"));
                    player.teleport(getDeathLocation(player));
                    removeDeathLocation(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Death location removed"));
                } else {
                    setLastLocation(player);
                    getLastLocation(player);
                }
            }else{
                setLastLocation(player);
                getLastLocation(player);
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> empty = new ArrayList<>();
        return empty;
    }
    private void getLastLocation(Player player){
        if (PlayerSettings.hasLastLocation(player)){
            PlayerSettings.getLastLocation(player).getChunk().load();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting&f back"));
            player.teleport(PlayerSettings.getLastLocation(player));
        }
    }
    private boolean hasDeathLocation(Player player){
        return PlayerConfig.get(player).getKeys(false).contains("death-location");
    }
    private Location getDeathLocation(Player player){
        World world = player.getServer().getWorld(PlayerConfig.get(player).getString("death-location.world"));
        double x = PlayerConfig.get(player).getDouble("death-location.x");
        double y = PlayerConfig.get(player).getDouble("death-location.y");
        double z = PlayerConfig.get(player).getDouble("death-location.z");
        float yaw = PlayerConfig.get(player).getLong("death-location.yaw");
        float pitch = PlayerConfig.get(player).getLong("death-location.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
    private void removeDeathLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("death-location",null);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
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