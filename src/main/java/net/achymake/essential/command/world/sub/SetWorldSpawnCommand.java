package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetWorldSpawnCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "setspawn";
    }

    @Override
    public String getDescription() {
        return "set world spawn";
    }

    @Override
    public String getSyntax() {
        return "/worlds setspawn";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            player.getWorld().setSpawnLocation(player.getLocation());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getWorld().getName()+"&6 changed spawn point"));
        }
    }
}