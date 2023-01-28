package net.achymake.essential.command.rtp;

import net.achymake.essential.files.Config;
import net.achymake.essential.settings.LocationSettings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RTPCommand implements CommandExecutor, TabCompleter {
    private static final HashMap<UUID, Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            if (player.hasPermission("essential.cooldown.exempt")){
                LocationSettings.RTPLocation(player);
            }else if (!cooldown.containsKey(player.getUniqueId())){
                cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                LocationSettings.RTPLocation(player);
            }else{
                Long timeElapsed =System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                String cooldownTimer = Config.get().getString("commands.cooldown.rtp");
                Integer integer = Integer.valueOf(cooldownTimer.replace(cooldownTimer,cooldownTimer+"000"));
                if (timeElapsed > integer){
                    cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                    LocationSettings.RTPLocation(player);
                }else{
                    long timer = (integer-timeElapsed);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&',"&cYou cannot use this until&f "+String.valueOf(timer).substring(0,String.valueOf(timer).length()-3)+" &cseconds")));
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