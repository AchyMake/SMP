package net.achymake.essential.command.back;

import net.achymake.essential.settings.PlayerSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BackCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("essential.back.death")) {
                if (PlayerSettings.hasDeathLocation(player)){
                    PlayerSettings.getDeathLocation(player).getChunk().load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting to &fDeath Location"));
                    player.teleport(PlayerSettings.getDeathLocation(player));
                    PlayerSettings.removeDeathLocation(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Death location removed"));
                } else {
                    getLastLocation(player);
                }
            }else{
                getLastLocation(player);
            }
        }
        return true;
    }
    private static void getLastLocation(Player player){
        if (PlayerSettings.hasLastLocation(player)){
            PlayerSettings.getLastLocation(player).getChunk().load();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Teleporting &fBack"));
            player.teleport(PlayerSettings.getLastLocation(player));
        }
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> empty = new ArrayList<>();
        return empty;
    }
}