package net.achymake.essential.api;

import net.achymake.essential.Essential;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class VaultSetup {
    private static boolean isVaultEnabled() {
        return Bukkit.getPluginManager().getPlugin("Vault") != null;
    }
    public static void setup(Essential plugin){
        if (isVaultEnabled()){
            try {
                Class.forName("net.milkbowl.vault.economy.Economy");
                plugin.getServer().getServicesManager().register(Economy.class, new VaultEconomyProvider(plugin), plugin, ServicePriority.Normal);
            } catch (ClassNotFoundException e) {
                Essential.instance.sendMessage(e.getMessage());
            }
        }else{
            plugin.sendMessage("You have to install 'Vault'");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}