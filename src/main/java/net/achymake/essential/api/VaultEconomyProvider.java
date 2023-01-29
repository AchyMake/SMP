package net.achymake.essential.api;

import net.achymake.essential.Essential;
import net.achymake.essential.files.Config;
import net.achymake.essential.files.PlayerConfig;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collections;
import java.util.List;

public class VaultEconomyProvider implements Economy {
    private final Essential ess;
    public VaultEconomyProvider(Essential plugin) {
        this.ess = plugin;
    }
    public boolean isEnabled() {
        return this.ess.isEnabled();
    }
    public String getName() {
        return "Essential";
    }
    public boolean hasBankSupport() {
        return false;
    }
    public int fractionalDigits() {
        return -1;
    }

    public String format(double amount) {
        return EconomyProvider.getFormat(amount);
    }
    public String currencyNamePlural() {
        return this.currencyNameSingular();
    }
    public String currencyNameSingular() {
        return Config.get().getString("settings.currency");
    }
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return PlayerConfig.exist(offlinePlayer);
    }
    public boolean hasAccount(String playerName) {
        return PlayerConfig.exist(Bukkit.getServer().getOfflinePlayer(playerName));
    }
    public boolean hasAccount(String playerName, String worldName) {
        return this.hasAccount(playerName);
    }
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return this.hasAccount(player);
    }
    public double getBalance(OfflinePlayer offlinePlayer) {
        return EconomyProvider.getEconomy(offlinePlayer);
    }
    public double getBalance(String playerName) {
        return EconomyProvider.getEconomy(Bukkit.getOfflinePlayer(playerName));
    }
    public double getBalance(String playerName, String world) {
        return this.getBalance(playerName);
    }
    public double getBalance(OfflinePlayer player, String world) {
        return this.getBalance(player);
    }
    public boolean has(OfflinePlayer offlinePlayer, double amount) {
        return EconomyProvider.getEconomy(offlinePlayer) >= amount;
    }
    public boolean has(String playerName, double amount) {
        return EconomyProvider.getEconomy(Bukkit.getOfflinePlayer(playerName)) >= amount;
    }
    public boolean has(String playerName, String worldName, double amount) {
        return this.has(playerName, amount);
    }
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return this.has(player, amount);
    }
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        if (offlinePlayer == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player cannot be null!");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        } else {
            EconomyProvider.removeEconomy(offlinePlayer,amount);
            return new EconomyResponse(amount, this.getBalance(offlinePlayer), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player name cannot be null!");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        } else {
            EconomyProvider.removeEconomy(Bukkit.getOfflinePlayer(playerName),amount);
            return new EconomyResponse(amount, this.getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return this.withdrawPlayer(playerName, amount);
    }
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.withdrawPlayer(player, amount);
    }
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        if (offlinePlayer == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player can not be null.");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        } else {
            EconomyProvider.addEconomy(offlinePlayer,amount);
            return new EconomyResponse(amount, this.getBalance(offlinePlayer), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player name can not be null.");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        } else {
            EconomyProvider.addEconomy(Bukkit.getOfflinePlayer(playerName),amount);
            return new EconomyResponse(amount, this.getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return this.depositPlayer(playerName, amount);
    }
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.depositPlayer(player, amount);
    }
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        PlayerConfig.create(offlinePlayer);
        return true;
    }
    public boolean createPlayerAccount(String playerName) {
        PlayerConfig.create(Bukkit.getOfflinePlayer(playerName));
        return true;
    }
    public boolean createPlayerAccount(String playerName, String worldName) {
        return this.createPlayerAccount(playerName);
    }
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return this.createPlayerAccount(player);
    }
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public List<String> getBanks() {
        return Collections.emptyList();
    }
}