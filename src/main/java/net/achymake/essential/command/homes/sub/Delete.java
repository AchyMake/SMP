package net.achymake.essential.command.homes.sub;

import net.achymake.essential.command.homes.HomesSubCommand;
import net.achymake.essential.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class Delete extends HomesSubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "deletes others home";
    }

    @Override
    public String getSyntax() {
        return "/homes delete player home";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("essential.homes.delete")){
            if (args.length == 3) {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                String homeName = args[2];
                if (PlayerConfig.get(offlinePlayer).getConfigurationSection("homes").getKeys(false).contains(homeName)){
                    PlayerConfig.setString(offlinePlayer,"homes."+homeName,null);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&6Deleted &f{1}&6 of &f{0}",offlinePlayer.getName(),homeName)));
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format("&cHome &f{1}&6of &f{0}&c does not exist",offlinePlayer.getName(),homeName)));
                }
            }
        }
    }
}