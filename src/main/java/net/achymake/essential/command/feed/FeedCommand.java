package net.achymake.essential.command.feed;

import net.achymake.essential.files.Config;
import net.achymake.essential.files.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FeedCommand implements CommandExecutor, TabCompleter {
    private static final HashMap<UUID, Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (player.hasPermission("essential.cooldown.exempt")){
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Your starvation has been satisfied"));
                }else if (!cooldown.containsKey(player.getUniqueId())){
                    cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Your starvation has been satisfied"));
                }else{
                    Long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                    String cooldownTimer = Config.get().getString("commands.cooldown.feed");
                    Integer integer = Integer.valueOf(cooldownTimer.replace(cooldownTimer, cooldownTimer + "000"));
                    if (timeElapsed > integer){
                        cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Your starvation has been satisfied"));
                    }else{
                        long timer = (integer-timeElapsed);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have to wait &f"+String.valueOf(timer).substring(0,String.valueOf(timer).length()-3)+"&c seconds"));
                    }
                }
            } else if (args.length == 1) {
                Player player = (Player) sender;
                if (sender.hasPermission("essential.feed.others")){
                    Player target = sender.getServer().getPlayerExact(args[0]);
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-offline"),args[0])));
                    }else{
                        target.setFoodLevel(20);
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',player.getName()+"&6 satisfied your starvation"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You satisfied &f"+target.getName()+"&6 starvation"));
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
}