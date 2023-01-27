package net.achymake.essential.command.world.sub;

import net.achymake.essential.Essential;
import net.achymake.essential.command.world.WorldSubCommand;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EditCommand extends WorldSubCommand {
    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public String getDescription() {
        return "allows to edit world";
    }

    @Override
    public String getSyntax() {
        return "/world edit";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            toggleWorldEdit(player);
        }
    }
    private boolean hasWorldEdit(Player player){
        return PlayerConfig.get(player).getBoolean("world-edit");
    }
    private void toggleWorldEdit(Player player){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+player.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (hasWorldEdit(player)){
            config.set("world-edit",null);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You toggled Edit World to off"));
        }else{
            config.set("world-edit",true);
            try {
                config.save(file);
            } catch (IOException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You toggled Edit World to on"));
        }
    }
}