package net.achymake.essential.command.homes.sub;

import net.achymake.essential.command.homes.HomesSubCommand;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Teleport extends HomesSubCommand {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public String getDescription() {
        return "teleports to players homes";
    }

    @Override
    public String getSyntax() {
        return "/homes teleport player home";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("essential.homes.teleport")){
            if (args.length == 3) {
                OfflinePlayer offlinePlayer = player.getServer().getOfflinePlayer(args[1]);
                String homeName = args[2];
                if (PlayerConfig.get(offlinePlayer).getConfigurationSection("homes").getKeys(false).contains(homeName)){
                    String worldName = PlayerConfig.get(offlinePlayer).getString("homes."+homeName+".world");
                    double x = PlayerConfig.get(offlinePlayer).getDouble("homes."+homeName+".x");
                    double y = PlayerConfig.get(offlinePlayer).getDouble("homes."+homeName+".y");
                    double z = PlayerConfig.get(offlinePlayer).getDouble("homes."+homeName+".z");
                    float yaw = PlayerConfig.get(offlinePlayer).getLong("homes."+homeName+".yaw");
                    float pitch = PlayerConfig.get(offlinePlayer).getLong("homes."+homeName+".pitch");
                    Location location = new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
                    location.getChunk().load();
                    player.teleport(location);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting &f"+homeName+ "&6 of &f"+offlinePlayer.getName()));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c do not have &f"+homeName));
                }
            }
        }
    }
}