package net.achymake.essential.command.jail;

import net.achymake.essential.Essential;
import net.achymake.essential.files.LocationConfig;
import net.achymake.essential.files.PlayerConfig;
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

public class JailCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1){
                Player player = (Player) sender;
                if (jailExist()) {
                    Player target = sender.getServer().getPlayerExact(args[0]);
                    if (target == player){
                        if (isJailed(target)){
                            toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You got free"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You freed "+target.getName()));
                        }else{
                            toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou just got jailed"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You just jailed "+target.getName()));
                        }
                    }else if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                    }else if (target.hasPermission("essential.jail.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to jail &f"+target.getName()));
                    }else{
                        if (isJailed(target)){
                            toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou just got jailed"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You just jailed "+target.getName()));
                        }else{
                            toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You got free"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You freed "+target.getName()));
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cJail has not been set"));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Player players : Bukkit.getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
    private boolean jailExist(){
        return LocationConfig.get().getKeys(false).contains("jail");
    }
    private Location getJail(){
        String worldName = LocationConfig.get().getString("jail.world");
        World world = Bukkit.getWorld(worldName);
        double x = LocationConfig.get().getDouble("jail.x");
        double y = LocationConfig.get().getDouble("jail.y");
        double z = LocationConfig.get().getDouble("jail.z");
        float yaw = LocationConfig.get().getLong("jail.yaw");
        float pitch = LocationConfig.get().getLong("jail.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
    private boolean isJailed(Player player){
        return PlayerConfig.get(player).getBoolean("jailed");
    }
    private void toggleJail(Player player){
        Location location = player.getLocation();
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getBoolean("jailed")){
            Location back = new Location(Bukkit.getWorld(config.getString("jail.world")),config.getDouble("jail.x"),config.getDouble("jail.y"),config.getDouble("jail.z"),config.getLong("jail.yaw"),config.getLong("jail.pitch"));
            player.teleport(back);
            config.set("jailed",false);
            config.set("jail",null);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            config.set("jailed",true);
            config.set("jail.world",location.getWorld().getName());
            config.set("jail.x",location.getX());
            config.set("jail.y",location.getY());
            config.set("jail.z",location.getZ());
            config.set("jail.yaw",location.getYaw());
            config.set("jail.pitch",location.getPitch());
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
            getJail().getChunk().load();
            player.teleport(getJail());
        }
    }
}