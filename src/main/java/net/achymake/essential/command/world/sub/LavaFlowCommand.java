package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;

public class LavaFlowCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "lava-flow";
    }

    @Override
    public String getDescription() {
        return "change lava flow for world";
    }

    @Override
    public String getSyntax() {
        return "/world lava-flow name value";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage: &f/world lava-flow world true"));
        }else if (args.length == 3) {
            String worldName = args[1];
            String value = args[2];
            changeLavaFlow(player,worldName,value);
        }
    }
    private File getWorldFolder(String worldName){
        return new File(Bukkit.getWorldContainer().getAbsoluteFile(), worldName);
    }
    private void changeLavaFlow(Player player,String worldName, String value){
        if (getWorldFolder(worldName).exists()){
            if (value.equalsIgnoreCase("true")){
                WorldConfig.get().set(worldName+".settings.lava-flow",true);
                WorldConfig.save();
            } else if (value.equalsIgnoreCase("false")) {
                WorldConfig.get().set(worldName+".settings.lava-flow",false);
                WorldConfig.save();
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 set lava-flow to &f"+value));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c does not exist"));
        }
    }
}