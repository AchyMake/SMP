package net.achymake.essential.command.tpa;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TPDenyCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (PlayerConfig.get(player).getKeys(false).contains("tpa-request-from")){
                Player target = Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(player).getString("tpa-request-from")));
                Bukkit.getScheduler().cancelTask(PlayerConfig.get(target).getInt("tpa-task"));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&c denied your tpa request"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You denied tpa request from &f"+target.getName()));
                Bukkit.getScheduler().cancelTask(PlayerConfig.get(target).getInt("tpa-task"));
                PlayerConfig.setString(target,"tpa-request-sent",null);
                PlayerConfig.setString(target,"tpa-task",null);
                PlayerConfig.setString(player,"tpa-request-from",null);
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
}