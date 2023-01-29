package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerPrepareAnvil implements Listener {
    public PlayerPrepareAnvil(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChatEvent (PrepareAnvilEvent event){
        if (event.getResult().getType().equals(Material.AIR))return;
        if (!event.getView().getPlayer().hasPermission("essential.chatcolor.anvil"))return;
        ItemMeta resultMeta = event.getResult().getItemMeta();
        resultMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', event.getInventory().getRenameText()));
        event.getResult().setItemMeta(resultMeta);
    }
}