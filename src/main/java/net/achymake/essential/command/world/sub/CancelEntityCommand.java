package net.achymake.essential.command.world.sub;

import net.achymake.essential.command.world.WorldSubCommand;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;

public class CancelEntityCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "cancel-entity";
    }

    @Override
    public String getDescription() {
        return "cancel entity events";
    }

    @Override
    public String getSyntax() {
        return "/world cancel-entity world entity value";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 4){
            String worldName = args[1];
            EntityType entityType = EntityType.valueOf(args[2].toUpperCase());
            String value = args[3];
            changeEntityEvents(player,worldName,value,entityType);
        }
    }
    private static void sendMessage(Player player, String worldName, EntityType entityType, String value){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 cancel &f"+entityType.toString().toLowerCase()+"&6 events set to &f"+value));
    }
    private File getWorldFolder(String worldName){
        return new File(Bukkit.getWorldContainer().getAbsoluteFile(), worldName);
    }
    private void changeEntityEvents(Player player, String worldName, String value, EntityType entityType){
        if (getWorldFolder(worldName).exists()){
            if (value.equalsIgnoreCase("true")){
                WorldConfig.get().set(worldName+".cancel-entity."+entityType,true);
                WorldConfig.save();
                sendEntityMessage(player,worldName,entityType,value);
            } else if (value.equalsIgnoreCase("false")) {
                WorldConfig.get().set(worldName+".cancel-entity."+entityType,null);
                WorldConfig.save();
                sendEntityMessage(player,worldName,entityType,value);
            }
        }else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&c does not exist"));
        }
    }
    private static void sendEntityMessage(Player player, String worldName, EntityType entityType, String value){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', worldName+"&6 set "+entityType.toString().toLowerCase()+" to &f"+value));
    }
}