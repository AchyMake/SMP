package net.achymake.essential.tablist;

import me.clip.placeholderapi.PlaceholderAPI;
import net.achymake.essential.Essential;
import net.achymake.essential.files.TablistConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tablist {
    public static List<Integer> count = new ArrayList<>();
    private static Collection<? extends Player> getOnlinePlayers(){
        return Bukkit.getOnlinePlayers();
    }
    public static void start(Essential plugin){
        count.add(0,0);
        count.add(1,0);
        int task_1 = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                if (count.get(0) >= TablistConfig.get().getStringList("header.list").size()) {
                    count.set(0, 0);
                }
                for (Player player : getOnlinePlayers()) {
                    player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&', TablistConfig.get().getStringList("header.list").get(count.get(0))));
                }
                if (count.get(0) < TablistConfig.get().getStringList("header.list").size()){
                    int yeet = 1+count.get(0);
                    count.set(0,yeet);
                }
            }
        }, 0, TablistConfig.get().getInt("header.tick")).getTaskId();
        plugin.tasks.add(0,task_1);
        int task_2 = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player : getOnlinePlayers()) {
                    player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player,"%essential_prefix%") + PlaceholderAPI.setPlaceholders(player,"%essential_player%") + PlaceholderAPI.setPlaceholders(player,"%essential_suffix%")));
                }
            }
        }, 0, 20).getTaskId();
        plugin.tasks.add(1,task_2);
        int task_3 = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                if (count.get(1) >= TablistConfig.get().getStringList("footer.list").size()) {
                    count.set(1,0);
                }
                for (Player player : getOnlinePlayers()) {
                    player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(TablistConfig.get().getStringList("footer.list").get(count.get(1)),Bukkit.getServer().getOnlinePlayers().size() - Essential.vanished.size(),Bukkit.getServer().getMaxPlayers())));
                }
                if (count.get(1) < TablistConfig.get().getStringList("footer.list").size()){
                    int yeet = 1+count.get(1);
                    count.set(1,yeet);
                }
            }
        }, 0, plugin.getConfig().getInt("footer.tick")).getTaskId();
        plugin.tasks.add(2,task_3);
    }
}