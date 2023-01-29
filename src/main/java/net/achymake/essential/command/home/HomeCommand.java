package net.achymake.essential.command.home;

import net.achymake.essential.files.Config;
import net.achymake.essential.files.PlayerConfig;
import net.achymake.essential.api.EconomyProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                String homeName = "home";
                if (PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).contains(homeName)){
                    getHome(player,homeName).getChunk().load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting &f"+homeName));
                    player.teleport(getHome(player,homeName));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',homeName+"&c does not exist"));
                }
            } else if (args.length == 1) {
                Player player = (Player) sender;
                String homeName = args[0];
                if (homeName.equalsIgnoreCase("buy")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Home costs &a"+ EconomyProvider.getFormat(Config.get().getDouble("homes.cost"))));
                } else if (homeName.equalsIgnoreCase("bed")) {
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
                }else if (PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false).contains(homeName)){
                    getHome(player,homeName).getChunk().load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting &f"+homeName));
                    player.teleport(getHome(player,homeName));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',homeName+"&c does not exist"));
                }
            }else if (args.length == 2) {
                Player player = (Player) sender;
                String homeName = args[0];
                if (homeName.equalsIgnoreCase("buy")) {
                    int amount = Integer.parseInt(args[1]);
                    if (player.hasPermission("essential.home.buy")){
                        if (Config.get().getDouble("home.cost") * amount > EconomyProvider.getEconomy(player)) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont have &a"+ EconomyProvider.getFormat(Config.get().getDouble("homes.cost"))));
                        } else {
                            PlayerConfig.setInt(player,"max-homes",amount);
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
            for (String home : PlayerConfig.get(player).getConfigurationSection("homes").getKeys(false)){
                commands.add(home);
            }
            if (sender.hasPermission("essential.home.bed")){
                commands.add("bed");
            }
            if (sender.hasPermission("essential.home.buy")){
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
    private static Location getHome(Player player, String homeName){
        String worldName = PlayerConfig.get(player).getString("homes."+homeName+".world");
        double x = PlayerConfig.get(player).getDouble("homes."+homeName+".x");
        double y = PlayerConfig.get(player).getDouble("homes."+homeName+".y");
        double z = PlayerConfig.get(player).getDouble("homes."+homeName+".z");
        float yaw = PlayerConfig.get(player).getLong("homes."+homeName+".yaw");
        float pitch = PlayerConfig.get(player).getLong("homes."+homeName+".pitch");
        return new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
    }
}