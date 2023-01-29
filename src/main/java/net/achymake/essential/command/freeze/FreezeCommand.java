package net.achymake.essential.command.freeze;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FreezeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1) {
                Player player = (Player) sender;
                Player target = player.getServer().getPlayer(args[0]);
                if (target == player){
                    toggleFreeze(player,target);
                }else if (target == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is offline"));
                }else{
                    if (target.hasPermission("essential.freeze.exempt")){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou are not allowed to freeze &f"+target.getName()));
                    }else{
                        toggleFreeze(player,target);
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
            for (Player players : sender.getServer().getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
    private void toggleFreeze(Player player, Player target){
        if (PlayerConfig.get(target).getBoolean("frozen")){
            PlayerConfig.toggle(target,"frozen");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You unfroze &f"+target.getName()));
        }else{
            PlayerConfig.toggle(target,"frozen");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You froze &f"+target.getName()));
        }

    }
}