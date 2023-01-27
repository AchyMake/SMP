package net.achymake.essential.listeners.block;

import net.achymake.essential.Essential;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class LavaFlow implements Listener {
    public LavaFlow(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (BlockFromToEvent event){
        if (!event.getBlock().getType().equals(Material.LAVA))return;
        if (WorldConfig.get().getBoolean(event.getBlock().getWorld().getName()+".settings.lava-flow"))return;
        if (event.getBlock().getBlockData() instanceof Levelled){
            Levelled levelled = (Levelled) event.getBlock().getBlockData();
            if (levelled.getLevel() == 0){
                event.setCancelled(true);
            }
        }
    }
}