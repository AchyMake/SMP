package net.achymake.essential.command.pvp;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PVPCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                togglePVP(player);
                sendMessage(player);
            } else if (args.length == 1) {
                Player player = (Player) sender;
                if (player.hasPermission("essential.pvp.others")){
                    Player target = player.getServer().getPlayerExact(args[0]);
                    if (target == player){
                        togglePVP(target);
                        sendMessageTarget(player,target);
                    }else if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is offline"));
                    }else if (target.hasPermission("essential.pvp.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to change pvp of &f"+target.getName()));
                    }else{
                        togglePVP(target);
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
    private void togglePVP(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getBoolean("pvp")){
            config.set("pvp",false);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            config.set("pvp",true);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }
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