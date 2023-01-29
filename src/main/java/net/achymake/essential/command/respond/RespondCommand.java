package net.achymake.essential.command.respond;

import net.achymake.essential.files.MessageConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RespondCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length > 0){
                Player player = (Player) sender;
                if (PlayerConfig.get(player).getKeys(false).contains("last-whisper")){
                    Player target = player.getServer().getPlayer(UUID.fromString(PlayerConfig.get(player).getString("last-whisper")));
                    if (target == null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command.error-target-null"),player.getServer().getOfflinePlayer(UUID.fromString(PlayerConfig.get(player).getString("last-whisper"))).getName())));
                    }else{
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String words : args){
                            stringBuilder.append(words);
                            stringBuilder.append(" ");
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7You > "+target.getName()+": "+stringBuilder));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7"+player.getName()+" > You: "+stringBuilder));
                        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&',"&7"+player.getName()+" > "+target.getName()+": "+stringBuilder),"essential.notify.whisper");
                        PlayerConfig.setString(target,"last-whisper",player.getUniqueId().toString());
                    }
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&fUnknown&c is either offline or has never joined"));
                }
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