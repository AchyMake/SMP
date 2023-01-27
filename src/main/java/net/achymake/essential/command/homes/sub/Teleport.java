package net.achymake.essential.command.homes.sub;

import net.achymake.essential.Essential;
import net.achymake.essential.command.homes.HomesSubCommand;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

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
                if (hasHomes(offlinePlayer)){
                    if (homeExist(offlinePlayer,homeName)){
                        getHome(offlinePlayer,homeName).getChunk().load();
                        player.teleport(getHome(offlinePlayer,homeName));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting &f"+homeName+ "&6 of &f"+offlinePlayer.getName()));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c do not have &f"+homeName));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c do not have any homes"));
                }
            }
        }
    }
    private boolean hasHomes(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getKeys(false).contains("homes");
    }
    private boolean homeExist(OfflinePlayer offlinePlayer, String homeName){
        return PlayerConfig.get(offlinePlayer).getKeys(true).contains("homes."+homeName);
    }
    private Location getHome(OfflinePlayer offlinePlayer, String home) {
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String world = config.getString("homes."+home+".world");
        double x = config.getDouble("homes."+home+".x");
        double y = config.getDouble("homes."+home+".y");
        double z = config.getDouble("homes."+home+".z");
        float yaw = config.getLong("homes."+home+".yaw");
        float pitch = config.getLong("homes."+home+".pitch");
        return new Location(Bukkit.getWorld(world),x,y,z,yaw,pitch);
    }
}