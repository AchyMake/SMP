package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RemoveCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "save and remove world";
    }

    @Override
    public String getSyntax() {
        return "/world remove name";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage: &f/world remove world"));
        }else if (args.length == 2) {
            String worldName = args[1];
            removeWorld(player,worldName);
        }
    }
    public static void removeWorld(Player player, String worldName){
        if (player.getServer().getWorlds().contains(Bukkit.getWorld(worldName))) {
            WorldConfig.get().set(worldName,null);
            WorldConfig.save();
            Bukkit.unloadWorld(worldName,true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',worldName+"&6 is saved and removed"));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c does not exist"));
        }
    }
}