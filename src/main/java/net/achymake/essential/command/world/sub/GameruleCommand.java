package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;

public class GameruleCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "gamerule";
    }

    @Override
    public String getDescription() {
        return "change gamerule";
    }

    @Override
    public String getSyntax() {
        return "/world gamerule world gamerule value";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 4) {
            String worldName = args[1];
            String gameRule = args[2];
            String value = args[3];
            changeGameRule(player,worldName,gameRule,value);
        }
    }
    private File getWorldFolder(String worldName){
        return new File(Bukkit.getWorldContainer().getAbsoluteFile(), worldName);
    }
    private void changeGameRule(Player player, String worldName, String gameRule, String value){
        if (getWorldFolder(worldName).exists()){
            Bukkit.getWorld(worldName).setGameRuleValue(gameRule,value);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 changed &f"+gameRule+"&6 to &f"+value));
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c does not exist"));
        }
    }
}