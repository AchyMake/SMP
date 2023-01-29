package net.achymake.essential.command.tpa;

import net.achymake.essential.Essential;
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

public class TPACommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1) {
                Player player = (Player) sender;
                Player target = player.getServer().getPlayerExact(args[0]);
                if (target == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is either offline or has never joined"));
                }else if (target == player){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou cant send tpa request to your self"));
                } else if (PlayerConfig.get(player).getKeys(false).contains("tpa-request-sent")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou already sent tpa request"));
                }else{
                    final int taskID = Bukkit.getScheduler().runTaskLater(Essential.instance, new Runnable() {
                        @Override
                        public void run() {
                            PlayerConfig.setString(target,"tpa-request-sent",null);
                            PlayerConfig.setString(target,"tpa-task",null);
                            PlayerConfig.setString(player,"tpa-request-from",null);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cTeleport request has been expired"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cTeleport request has been expired"));
                        }
                    }, 300).getTaskId();
                    PlayerConfig.setInt(player,"tpa-task",taskID);
                    PlayerConfig.setString(player,"tpa-request-send",target.getUniqueId().toString());
                    PlayerConfig.setString(target,"tpa-request-from",player.getUniqueId().toString());
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 sent you a tpa request"));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You can type &a/tpaccept&6 or &c/tpdeny"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You have sent tpa request to &f"+target.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You can type &c/tpcancel"));
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
}