package net.achymake.essential.command.chatcolor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatColorCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GOLD+"Minecraft colors:");
                player.sendMessage(ChatColor.BLACK+"&0"+ChatColor.DARK_BLUE+" &1"+ChatColor.DARK_GREEN+" &2"+ChatColor.DARK_AQUA+" &3");
                player.sendMessage(ChatColor.DARK_RED+"&4"+ChatColor.DARK_PURPLE+" &5"+ChatColor.GOLD+" &6"+ChatColor.GRAY+" &7");
                player.sendMessage(ChatColor.DARK_GRAY+"&8"+ChatColor.BLUE+" &9"+ChatColor.GREEN+" &a"+ChatColor.AQUA+" &b");
                player.sendMessage(ChatColor.RED+"&c"+ChatColor.LIGHT_PURPLE+" &d"+ChatColor.YELLOW+" &e");
                player.sendMessage("");
                player.sendMessage("&k"+ChatColor.MAGIC+" magic"+ChatColor.RESET+" &l"+ChatColor.BOLD+" Bold");
                player.sendMessage("&m"+ChatColor.STRIKETHROUGH+" Strike"+ChatColor.RESET+" &n"+ChatColor.UNDERLINE+" Underline");
                player.sendMessage("&o"+ChatColor.ITALIC+" Italic"+ChatColor.RESET+" &r Reset");
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
}