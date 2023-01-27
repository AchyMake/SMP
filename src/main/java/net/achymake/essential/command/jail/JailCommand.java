package net.achymake.essential.command.jail;

import net.achymake.essential.files.LocationConfig;
import net.achymake.essential.settings.PlayerSettings;
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

public class JailCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1){
                Player player = (Player) sender;
                if (jailExist()) {
                    Player target = sender.getServer().getPlayerExact(args[0]);
                    if (target == player){
                        if (PlayerSettings.isJailed(target)){
                            PlayerSettings.toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You got free"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You freed "+target.getName()));
                        }else{
                            PlayerSettings.toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou just got jailed"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You just jailed "+target.getName()));
                        }
                    }else if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                    }else if (target.hasPermission("essential.jail.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to jail &f"+target.getName()));
                    }else{
                        if (PlayerSettings.isJailed(target)){
                            PlayerSettings.toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou just got jailed"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You just jailed "+target.getName()));
                        }else{
                            PlayerSettings.toggleJail(target);
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
    public static boolean jailExist(){
        return LocationConfig.get().getKeys(false).contains("jail");
    }
    public static Location getJail(){
        String worldName = LocationConfig.get().getString("jail.world");
        World world = Bukkit.getWorld(worldName);
        double x = LocationConfig.get().getDouble("jail.x");
        double y = LocationConfig.get().getDouble("jail.y");
        double z = LocationConfig.get().getDouble("jail.z");
        float yaw = LocationConfig.get().getLong("jail.yaw");
        float pitch = LocationConfig.get().getLong("jail.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
}