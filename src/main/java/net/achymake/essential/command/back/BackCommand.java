package net.achymake.essential.command.back;

import net.achymake.essential.files.MessageConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BackCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("essential.back.death")) {
                if (hasDeathLocation(player)){
                    getDeathLocation(player).getChunk().load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.get().getString("command.back-death")));
                    player.teleport(getDeathLocation(player));
                    removeDeathLocation(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.get().getString("command.back-death-removal")));
                } else {
                    getLastLocation(player).getChunk().load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.get().getString("command.back")));
                    player.teleport(getLastLocation(player));
                }
            }else{
                getLastLocation(player).getChunk().load();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.get().getString("command.back")));
                player.teleport(getLastLocation(player));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> empty = new ArrayList<>();
        return empty;
    }
    private Location getLastLocation(Player player){
        String worldName = PlayerConfig.get(player).getString("last-location.world");
        double x = PlayerConfig.get(player).getDouble("last-location.x");
        double y = PlayerConfig.get(player).getDouble("last-location.y");
        double z = PlayerConfig.get(player).getDouble("last-location.z");
        float yaw = PlayerConfig.get(player).getLong("last-location.yaw");
        float pitch = PlayerConfig.get(player).getLong("last-location.pitch");
        return new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
    }
    private boolean hasDeathLocation(Player player){
        return PlayerConfig.get(player).getKeys(false).contains("death-location");
    }
    private Location getDeathLocation(Player player){
        String worldName = PlayerConfig.get(player).getString("death-location.world");
        double x = PlayerConfig.get(player).getDouble("death-location.x");
        double y = PlayerConfig.get(player).getDouble("death-location.y");
        double z = PlayerConfig.get(player).getDouble("death-location.z");
        float yaw = PlayerConfig.get(player).getLong("death-location.yaw");
        float pitch = PlayerConfig.get(player).getLong("death-location.pitch");
        return new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
    }
    private void removeDeathLocation(Player player){
        PlayerConfig.setString(player,"death-location",null);
    }
}