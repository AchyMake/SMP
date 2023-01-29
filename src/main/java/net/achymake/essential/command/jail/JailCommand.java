package net.achymake.essential.command.jail;

import net.achymake.essential.files.LocationConfig;
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class JailCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1){
                Player player = (Player) sender;
                if (LocationConfig.locationExist("jail")) {
                    Player target = sender.getServer().getPlayerExact(args[0]);
                    if (target == player){
                        if (PlayerConfig.get(target).getBoolean("jailed")){
                            toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You got free"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You freed "+target.getName()));
                        }else{
                            toggleJail(target);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou just got jailed"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You just jailed "+target.getName()));
                        }
                    }else if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-offline"),args[0])));
                    }else if (target.hasPermission("essential.jail.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to jail &f"+target.getName()));
                    }else{
                        if (PlayerConfig.get(target).getBoolean("jailed")){
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
    private void toggleJail(Player player){
        if (PlayerConfig.get(player).getBoolean("jailed")){
            PlayerConfig.toggle(player,"jailed");
            String worldName = PlayerConfig.get(player).getString("jail.world");
            double x = PlayerConfig.get(player).getDouble("jail.x");
            double y = PlayerConfig.get(player).getDouble("jail.y");
            double z = PlayerConfig.get(player).getDouble("jail.z");
            float yaw = PlayerConfig.get(player).getLong("jail.yaw");
            float pitch = PlayerConfig.get(player).getLong("jail.pitch");
            Location back = new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
            back.getChunk().load();
            player.teleport(back);
            PlayerConfig.setString(player,"jail",null);
        }else{
            PlayerConfig.toggle(player,"jailed");
            PlayerConfig.setLocation(player,"jail");
            LocationConfig.getLocation("jail").getChunk().load();
            player.teleport(LocationConfig.getLocation("jail"));
        }
    }
}