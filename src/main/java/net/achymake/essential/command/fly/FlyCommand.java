package net.achymake.essential.command.fly;

import net.achymake.essential.files.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                toggleFly(player);
            } else if (args.length == 1) {
                if (player.hasPermission("essential.fly.others")){
                    Player target = player.getServer().getPlayerExact(args[0]);
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-offline"),args[0])));
                    }else{
                        if (target == player){
                            toggleFly(player);
                        }else if (target.hasPermission("essential.fly.exempt")){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to toggle fly for &f"+target.getName()));
                        }else{
                            toggleFly(player);
                        }
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            if (sender.hasPermission("essential.fly.others")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
                return commands;
            }
        }
        return commands;
    }
    private void toggleFly(Player player){
        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Fly mode &cdisabled"));
        }else{
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Fly mode &aenabled"));
        }
    }
}