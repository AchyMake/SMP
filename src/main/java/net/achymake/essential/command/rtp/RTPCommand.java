package net.achymake.essential.command.rtp;

import net.achymake.essential.files.Config;
import net.achymake.essential.settings.PlayerSettings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class RTPCommand implements CommandExecutor, TabCompleter {
    private static final HashMap<UUID, Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            if (player.hasPermission("essential.cooldown.exempt")){
                PlayerSettings.setLastLocation(player);
                newLocation(player);
            }else if (!cooldown.containsKey(player.getUniqueId())){
                cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                PlayerSettings.setLastLocation(player);
                newLocation(player);
            }else{
                Long timeElapsed =System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                String cooldownTimer = Config.get().getString("commands.cooldown.rtp");
                Integer integer = Integer.valueOf(cooldownTimer.replace(cooldownTimer,cooldownTimer+"000"));
                if (timeElapsed > integer){
                    cooldown.put(player.getUniqueId(),System.currentTimeMillis());
                    PlayerSettings.setLastLocation(player);
                    newLocation(player);
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
    private void newLocation(Player player){
        World rtpWorld = Bukkit.getWorld(Config.get().getString("commands.rtp.world"));
        Random random = new Random();
        Location location = rtpWorld.getHighestBlockAt(random.nextInt(0, Config.get().getInt("commands.rtp.spread")),random.nextInt(0, Config.get().getInt("commands.rtp.spread"))).getLocation();
        if (Config.get().getStringList("commands.rtp.safe-materials").contains(location.getBlock().getType().toString())){
            location.getChunk().load();
            if (location.getBlock().getType().equals(Material.GRASS)){
                player.teleport(location.add(0.5,0,0.5));
            }else{
                player.teleport(location.add(0.5,1,0.5));
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleported to X:&f "+location.getBlockX()+"&6 Z:&f "+location.getBlockZ()));
        }else{
            newLocation(player);
        }
    }
}