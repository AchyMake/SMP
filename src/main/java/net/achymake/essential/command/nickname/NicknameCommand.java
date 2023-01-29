package net.achymake.essential.command.nickname;

import net.achymake.essential.files.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NicknameCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            Player player = (Player) sender;
            PlayerConfig.setString(player,"custom-name",null);
            player.setCustomName(null);
            player.setCustomNameVisible(false);
        }else if (args.length == 1){
            Player player = (Player) sender;
            String nickname = args[0];
            player.setCustomName(ChatColor.translateAlternateColorCodes('&',nickname));
            PlayerConfig.setString(player,"custom-name",nickname);
            player.setCustomNameVisible(true);
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Player players : sender.getServer().getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }
        return commands;
    }
}