package net.achymake.essential.listeners.connection;

import net.achymake.essential.Essential;
import net.achymake.essential.files.LocationConfig;
import net.achymake.essential.files.MotdConfig;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class PlayerJoinSetup implements Listener {
    public PlayerJoinSetup(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinSetup (PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (PlayerConfig.get(player).getBoolean("new")){
            setupPlayer(player);
        }
    }
    private void setupPlayer(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (spawnExist()){
            getSpawn().getChunk().load();
            player.teleport(getSpawn());
            for (Player players : player.getServer().getOnlinePlayers()){
                for (String welcome : MotdConfig.get().getStringList("welcome")){
                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(welcome,player.getName())));
                }
            }
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"Spawn &chas not been set"));
        }
        config.set("new",null);
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    private boolean spawnExist(){
        return LocationConfig.get().getKeys(false).contains("spawn");
    }
    private Location getSpawn(){
        String worldName = LocationConfig.get().getString("spawn.world");
        World world = Bukkit.getWorld(worldName);
        double x = LocationConfig.get().getDouble("spawn.x");
        double y = LocationConfig.get().getDouble("spawn.y");
        double z = LocationConfig.get().getDouble("spawn.z");
        float yaw = LocationConfig.get().getLong("spawn.yaw");
        float pitch = LocationConfig.get().getLong("spawn.pitch");
        return new Location(world,x,y,z,yaw,pitch);
    }
}