package net.achymake.essential.listeners.interact;

import net.achymake.essential.Essential;
import net.achymake.essential.files.ExperienceConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArrowInteract implements Listener {
    public ArrowInteract(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (PlayerInteractEvent event){
        if (!ExperienceConfig.get().getBoolean("settings.player-throw-arrows.enable"))return;
        if (!event.getPlayer().hasPermission("essential.skills"))return;
        if (event.getPlayer().getLevel() >= ExperienceConfig.get().getInt("settings.player-throw-arrows.max-level")){
            if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ARROW))return;
            if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))return;
            if (event.getPlayer().hasCooldown(event.getPlayer().getInventory().getItemInMainHand().getType()))return;
            event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_TRIDENT_THROW,0.25F,1.2F);
            event.getPlayer().launchProjectile(Arrow.class);
            event.getPlayer().setCooldown(event.getPlayer().getInventory().getItemInMainHand().getType(),10);
        }
    }
}