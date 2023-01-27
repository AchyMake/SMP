package net.achymake.essential.command.suffix;

import net.achymake.essential.Essential;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SuffixCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayer(args[0]);
            resetSuffix(offlinePlayer);
        }else if (args.length > 1){
            OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayer(args[0]);
            if (PlayerConfig.exist(offlinePlayer)){
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 1; i < args.length; i++){
                    stringBuilder.append(args[i]);
                    stringBuilder.append(" ");
                }
                setSuffix(offlinePlayer,stringBuilder);
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                commands.add(offlinePlayer.getName());
            }
            return commands;
        }
        return commands;
    }
    private void setSuffix(OfflinePlayer offlinePlayer, StringBuilder stringBuilder){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("suffix",stringBuilder.toString().stripTrailing());
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    private void resetSuffix(OfflinePlayer offlinePlayer){
        File file = new File(Essential.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("suffix","");
        try {
            config.save(file);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
}