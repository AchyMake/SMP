package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;

public class CreateCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "create new world";
    }

    @Override
    public String getSyntax() {
        return "/world create name";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 2){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cusage: &f/world create world normal"));
        }else if (args.length == 3) {
            String worldName = args[1];
            String worldEnvironment = args[2].toUpperCase();
            World.Environment environment = World.Environment.valueOf(worldEnvironment);
            createWorld(player,worldName,environment);
        }
    }
    private File getWorldFolder(String worldName){
        return new File(Bukkit.getWorldContainer().getAbsoluteFile(), worldName);
    }
    private void worldCreation(String worldName, World.Environment environment){
        WorldCreator worldCreator = new WorldCreator(worldName);
        WorldConfig.get().set(worldName + ".lava-flow",true);
        WorldConfig.get().set(worldName + ".environment",environment.toString());
        WorldConfig.get().set(worldName + ".seed",worldCreator.seed());
        worldCreator.environment(environment);
        worldCreator.createWorld();
        WorldConfig.save();
    }
    private void createWorld(Player player, String worldName, World.Environment environment){
        if (!getWorldFolder(worldName).exists()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 is about to be created"));
            worldCreation(worldName,environment);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 created with environment &f"+environment.toString().toLowerCase()));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c already exist"));
        }
    }
}