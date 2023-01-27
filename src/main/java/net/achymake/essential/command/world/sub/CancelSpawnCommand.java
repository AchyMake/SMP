package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;

public class CancelSpawnCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "cancel-spawn";
    }

    @Override
    public String getDescription() {
        return "cancel spawn on entity";
    }

    @Override
    public String getSyntax() {
        return "/world cancel-spawn world entity value";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 4){
            String worldName = args[1];
            EntityType entityType = EntityType.valueOf(args[2].toUpperCase());
            String value = args[3];
            File world = new File(Bukkit.getWorldContainer().getAbsoluteFile(), worldName);
            if (world.exists()){
                if (value.equalsIgnoreCase("true")){
                    WorldConfig.get().set(worldName+".cancel-spawn."+entityType,true);
                    WorldConfig.save();
                    sendMessage(player,worldName,entityType,value);
                } else if (value.equalsIgnoreCase("false")) {
                    WorldConfig.get().set(worldName+".cancel-spawn."+entityType,null);
                    WorldConfig.save();
                    sendMessage(player,worldName,entityType,value);
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c does not exist"));
            }
        }
    }
    private static void sendMessage(Player player, String worldName, EntityType entityType, String value){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 cancel &f"+entityType.toString().toLowerCase()+"&6 spawning set to &f"+value));
    }
}