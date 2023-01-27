package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeleportCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public String getDescription() {
        return "teleport to different world";
    }

    @Override
    public String getSyntax() {
        return "/world teleport name";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage: &f/world teleport world"));
        }else if (args.length == 2) {
            String worldName = args[1];
            if (Bukkit.getWorlds().contains(Bukkit.getWorld(worldName))){
                player.teleport(Bukkit.getServer().getWorld(worldName).getSpawnLocation().add(0.5,0,0.5));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Teleporting to &f"+worldName));
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c does not exist"));
            }
        }
    }
}