package net.achymake.essential.api;

import net.achymake.essential.Essential;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;

public class VaultSetup {
    private static boolean setupEconomy(Essential plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        try {
            Class.forName("net.milkbowl.vault.economy.Economy");
            plugin.getServer().getServicesManager().register(Economy.class, new VaultEconomyProvider(plugin), plugin, ServicePriority.Normal);
        } catch (ClassNotFoundException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
        return true;
    }
    public static void setup(Essential plugin){
        if (!setupEconomy(plugin)){
            plugin.getLogger().severe(String.format("[%s] - no Vault found!", plugin.getName()));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}