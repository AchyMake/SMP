package net.achymake.essential.command.whisper;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WhisperCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length > 1){
                Player player = (Player) sender;
                Player target = player.getServer().getPlayerExact(args[0]);
                if (target == null){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',args[0]+"&c is offline"));
                }else{
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i = 1; i < args.length; i++){
                        stringBuilder.append(args[i]);
                        stringBuilder.append(" ");
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7You > "+target.getName()+": "+stringBuilder));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7"+player.getName()+" > You: "+stringBuilder));
                    player.getServer().broadcast(ChatColor.translateAlternateColorCodes('&',"&7"+player.getName()+" > "+target.getName()+": "+stringBuilder),"essential.notify.whisper");
                    PlayerConfig.setString(target,"last-whisper",player.getUniqueId().toString());
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