package net.achymake.essential.command.home;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import net.achymake.essential.files.PlayerConfig;
import net.achymake.essential.settings.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                if (PlayerConfig.get(player).getKeys(false).contains("homes")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have set &f"+ PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).size()+"&6 homes"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Your total home is &f"+ PlayerConfig.get(player).getInt("max-homes")));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou havent set any homes"));
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("buy")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home costs &a$"+ Economy.getFormat(Config.get().getDouble("home.cost"))));
                } else if (args[0].equalsIgnoreCase("bed")) {
                    if (player.getBedSpawnLocation() != null){
                        if (player.hasPermission("essential.home.bed")){
                            Location location = player.getBedSpawnLocation();
                            location.setPitch(player.getLocation().getPitch());
                            location.setYaw(player.getLocation().getYaw());
                            player.getBedSpawnLocation().getChunk().load();
                            player.teleport(location);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting &f"+args[0]));
                        }
                    }
                }else if (PlayerConfig.get(player).getKeys(false).contains("homes."+args[0])){
                    getHome(player,args[0]).getChunk().load();
                    player.teleport(getHome(player,args[0]));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cHome &f"+args[0]+"&c does not exist"));
                }
            }else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("buy")) {
                    if (player.hasPermission("essential.home.buy")){
                        if (Config.get().getDouble("home.cost") * Double.parseDouble(args[1]) > Economy.getEconomy(player)) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont have enough"));
                        } else {
                            File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
                            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                            config.set("max-homes", config.getInt("max-homes") + Integer.parseInt(args[1]));
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                Essential.instance.sendMessage(e.getMessage());
                            }
                            Economy.removeEconomy(player, Config.get().getDouble("home.cost") * Double.parseDouble(args[1]));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You bought &f" + args[1] + "&6 for &c$" + Config.get().getDouble("home.cost") * Double.parseDouble(args[1])));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You now have &f" + PlayerConfig.get(player).getInt("max-homes") + "&6 total homes"));
                        }
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        Player player = (Player) sender;
        if (args.length == 1) {
            if (PlayerConfig.get(player).getKeys(false).contains("homes")){
                for (String home : PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false)){
                    commands.add(home);
                }
            }
            if (player.hasPermission("essential.home.bed")){
                if (player.getBedSpawnLocation() != null){
                    commands.add("bed");
                }
            }
            if (player.hasPermission("essential.home.buy")){
                commands.add("buy");
            }
            return commands;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("buy")){
                commands.add("1");
                commands.add("2");
                commands.add("3");
                return commands;
            }
        }
        return commands;
    }
    public static Location getHome(OfflinePlayer offlinePlayer, String home) {
        String world = PlayerConfig.get(offlinePlayer).getString("homes."+home+".world");
        double x = PlayerConfig.get(offlinePlayer).getDouble("homes."+home+".x");
        double y = PlayerConfig.get(offlinePlayer).getDouble("homes."+home+".y");
        double z = PlayerConfig.get(offlinePlayer).getDouble("homes."+home+".z");
        float yaw = PlayerConfig.get(offlinePlayer).getLong("homes."+home+".yaw");
        float pitch = PlayerConfig.get(offlinePlayer).getLong("homes."+home+".pitch");
        return new Location(Bukkit.getWorld(world),x,y,z,yaw,pitch);
    }
}