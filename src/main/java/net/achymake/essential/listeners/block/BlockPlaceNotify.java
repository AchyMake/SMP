package net.achymake.essential.listeners.block;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceNotify implements Listener {
    public BlockPlaceNotify(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLoginSetup (BlockPlaceEvent event){
    }
}