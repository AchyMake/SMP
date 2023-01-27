package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerTurtleEggBreak implements Listener {
    public PlayerTurtleEggBreak(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (PlayerInteractEvent event){
        if (WorldConfig.get().getBoolean(event.getPlayer().getWorld().getName()+".settings.physical.turtle-egg-break"))return;
        if (event.getClickedBlock() == null) return;
        if (!event.getAction().equals(Action.PHYSICAL))return;
        if (!event.getClickedBlock().getType().equals(Material.TURTLE_EGG))return;
        event.setCancelled(true);
    }
}