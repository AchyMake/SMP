package net.achymake.essential.command.eco.sub;

import net.achymake.essential.command.eco.EcoSubCommand;
import net.achymake.essential.files.PlayerConfig;
import net.achymake.essential.settings.Economy;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Set extends EcoSubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "set eco to player account";
    }

    @Override
    public String getSyntax() {
        return "/eco set target amount";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3){
            OfflinePlayer offlinePlayer = player.getServer().getOfflinePlayer(args[1]);
            double value = Double.parseDouble(args[2]);
            if (PlayerConfig.exist(offlinePlayer)){
                Economy.setEconomy(offlinePlayer, value);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You added &a" + Economy.getFormat(value) + "&6 to &f" + offlinePlayer.getName()));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', offlinePlayer.getName() + "&6 has now &a" + Economy.getFormat(Economy.getEconomy(offlinePlayer))));
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',offlinePlayer.getName()+"&c has never joined"));
            }
        }
    }
}