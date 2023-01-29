package net.achymake.essential.command.pvp;

import net.achymake.essential.files.MessageConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PVPCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (PlayerConfig.get(player).getBoolean("pvp")){
                    PlayerConfig.toggle(player,"pvp");
                }else{
                    PlayerConfig.toggle(player,"pvp");
                }
                sendMessage(player);
            } else if (args.length == 1) {
                Player player = (Player) sender;
                if (player.hasPermission("essential.pvp.others")){
                    Player target = player.getServer().getPlayerExact(args[0]);
                    if (target == player){
                        if (PlayerConfig.get(player).getBoolean("pvp")){
                            PlayerConfig.toggle(player,"pvp");
                        }else{
                            PlayerConfig.toggle(player,"pvp");
                        }
                        sendMessageTarget(player,target);
                    }else if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-offline"),args[0])));
                    }else if (target.hasPermission("essential.pvp.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to change pvp of &f"+target.getName()));
                    }else{
                        if (PlayerConfig.get(target).getBoolean("pvp")){
                            PlayerConfig.toggle(target,"pvp");
                        }else{
                            PlayerConfig.toggle(target,"pvp");
                        }
                        sendMessageTarget(player,target);
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
            if (sender.hasPermission("essential.pvp.others")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
                return commands;
            }
        }
        return commands;
    }
    private boolean hasPVP(Player player){
        return PlayerConfig.get(player).getBoolean("pvp");
    }
    private void sendMessage(Player player){
        if (hasPVP(player)){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&6You changed your pvp to &c{0}",hasPVP(player))));
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&6You changed your pvp to &a{0}",hasPVP(player))));
        }
    }
    private void sendMessageTarget(Player player,Player target){
        if (hasPVP(target)){
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&6You changed &f{0}&6 pvp to &c{1}",target.getName(),hasPVP(target))));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("{0}&6 changed your pvp to &c{1}",player.getName(),hasPVP(target))));
        }else{
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&6You changed &f{0}&6 pvp to &a{1}",target.getName(),hasPVP(target))));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("{0}&6 changed your pvp to &a{1}",player.getName(),hasPVP(target))));
        }
    }
}