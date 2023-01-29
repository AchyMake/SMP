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

public class TPCancelCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (PlayerConfig.get(player).getKeys(false).contains("tpa-task")){
                Bukkit.getScheduler().cancelTask(PlayerConfig.get(player).getInt("tpa-task"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You cancel tpa request"));
                Player target = Bukkit.getServer().getPlayer(UUID.fromString(PlayerConfig.get(player).getString("tpa-request-sent")));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getName()+"&c cancel tpa request"));
                Bukkit.getScheduler().cancelTask(PlayerConfig.get(player).getInt("tpa-task"));
                PlayerConfig.setString(player,"tpa-request-sent",null);
                PlayerConfig.setString(player,"tpa-task",null);
                PlayerConfig.setString(target,"tpa-request-from",null);
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