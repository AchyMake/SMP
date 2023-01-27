package net.achymake.essential.command.rtp;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import net.achymake.essential.settings.PlayerSettings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
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
                setLastLocation(player);
                player.teleport(location.add(0.5,0,0.5));
            }else{
                setLastLocation(player);
                player.teleport(location.add(0.5,1,0.5));
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleported to X:&f "+location.getBlockX()+"&6 Z:&f "+location.getBlockZ()));
        }else{
            newLocation(player);
        }
    }
    private void setLastLocation(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();
        config.set("last-location.world",location.getWorld().getName());
        config.set("last-location.x",location.getX());
        config.set("last-location.y",location.getY());
        config.set("last-location.z",location.getZ());
        config.set("last-location.yaw",location.getYaw());
        config.set("last-location.pitch",location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}