package net.achymake.essential.command;

import net.achymake.essential.Essential;
import net.achymake.essential.command.announcement.AnnouncementCommand;
import net.achymake.essential.command.anvil.AnvilCommand;
import net.achymake.essential.command.back.BackCommand;
import net.achymake.essential.command.balance.BalanceCommand;
import net.achymake.essential.command.ban.BanCommand;
import net.achymake.essential.command.chatcolor.ChatColorCommand;
import net.achymake.essential.command.eco.EcoCommand;
import net.achymake.essential.command.enchant.EnchantCommand;
import net.achymake.essential.command.enderchest.EnderchestCommand;
import net.achymake.essential.command.feed.FeedCommand;
import net.achymake.essential.command.fly.FlyCommand;
import net.achymake.essential.command.freeze.FreezeCommand;
import net.achymake.essential.command.gamemode.*;
import net.achymake.essential.command.give.GiveCommand;
import net.achymake.essential.command.hat.HatCommand;
import net.achymake.essential.command.heal.HealCommand;
import net.achymake.essential.command.help.HelpCommand;
import net.achymake.essential.command.home.DelhomeCommand;
import net.achymake.essential.command.home.HomeCommand;
import net.achymake.essential.command.home.SethomeCommand;
import net.achymake.essential.command.homes.HomesCommand;
import net.achymake.essential.command.info.InfoCommand;
import net.achymake.essential.command.inventory.InventoryCommand;
import net.achymake.essential.command.jail.JailCommand;
import net.achymake.essential.command.jail.SetjailCommand;
import net.achymake.essential.command.kick.KickCommand;
import net.achymake.essential.command.kit.KitCommand;
import net.achymake.essential.command.motd.MotdCommand;
import net.achymake.essential.command.mute.MuteCommand;
import net.achymake.essential.command.pay.PayCommand;
import net.achymake.essential.command.pvp.PVPCommand;
import net.achymake.essential.command.reload.EssentialCommand;
import net.achymake.essential.command.repair.RepairCommand;
import net.achymake.essential.command.respond.RespondCommand;
import net.achymake.essential.command.rules.RulesCommand;
import net.achymake.essential.command.skull.SkullCommand;
import net.achymake.essential.command.spawn.SetspawnCommand;
import net.achymake.essential.command.spawn.SpawnCommand;
import net.achymake.essential.command.spectate.SpectateCommand;
import net.achymake.essential.command.tp.TPCommand;
import net.achymake.essential.command.tp.TPHereCommand;
import net.achymake.essential.command.tpa.TPACommand;
import net.achymake.essential.command.tpa.TPAcceptCommand;
import net.achymake.essential.command.tpa.TPCancelCommand;
import net.achymake.essential.command.tpa.TPDenyCommand;
import net.achymake.essential.command.unban.UnbanCommand;
import net.achymake.essential.command.vanish.VanishCommand;
import net.achymake.essential.command.warp.DelwarpCommand;
import net.achymake.essential.command.warp.SetwarpCommand;
import net.achymake.essential.command.warp.WarpCommand;
import net.achymake.essential.command.whisper.WhisperCommand;
import net.achymake.essential.command.workbench.WorkbenchCommand;

public class Commands {
    public static void start(Essential plugin){
        plugin.getCommand("announcement").setExecutor(new AnnouncementCommand());
        plugin.getCommand("anvil").setExecutor(new AnvilCommand());
        plugin.getCommand("back").setExecutor(new BackCommand());
        plugin.getCommand("balance").setExecutor(new BalanceCommand());
        plugin.getCommand("ban").setExecutor(new BanCommand());
        plugin.getCommand("chatcolor").setExecutor(new ChatColorCommand());
        plugin.getCommand("eco").setExecutor(new EcoCommand());
        plugin.getCommand("enchant").setExecutor(new EnchantCommand());
        plugin.getCommand("enderchest").setExecutor(new EnderchestCommand());
        plugin.getCommand("feed").setExecutor(new FeedCommand());
        plugin.getCommand("fly").setExecutor(new FlyCommand());
        plugin.getCommand("freeze").setExecutor(new FreezeCommand());
        plugin.getCommand("gamemode").setExecutor(new GamemodeCommand());
        plugin.getCommand("gma").setExecutor(new GMACommand());
        plugin.getCommand("gmc").setExecutor(new GMCCommand());
        plugin.getCommand("gms").setExecutor(new GMSCommand());
        plugin.getCommand("gmsp").setExecutor(new GMSPCommand());
        plugin.getCommand("give").setExecutor(new GiveCommand());
        plugin.getCommand("hat").setExecutor(new HatCommand());
        plugin.getCommand("heal").setExecutor(new HealCommand());
        plugin.getCommand("help").setExecutor(new HelpCommand());
        plugin.getCommand("delhome").setExecutor(new DelhomeCommand());
        plugin.getCommand("home").setExecutor(new HomeCommand());
        plugin.getCommand("sethome").setExecutor(new SethomeCommand());
        plugin.getCommand("homes").setExecutor(new HomesCommand());
        plugin.getCommand("info").setExecutor(new InfoCommand());
        plugin.getCommand("inventory").setExecutor(new InventoryCommand());
        plugin.getCommand("jail").setExecutor(new JailCommand());
        plugin.getCommand("setjail").setExecutor(new SetjailCommand());
        plugin.getCommand("kick").setExecutor(new KickCommand());
        plugin.getCommand("kit").setExecutor(new KitCommand());
        plugin.getCommand("motd").setExecutor(new MotdCommand());
        plugin.getCommand("mute").setExecutor(new MuteCommand());
        plugin.getCommand("pay").setExecutor(new PayCommand());
        plugin.getCommand("pvp").setExecutor(new PVPCommand());
        plugin.getCommand("essential").setExecutor(new EssentialCommand());
        plugin.getCommand("repair").setExecutor(new RepairCommand());
        plugin.getCommand("respond").setExecutor(new RespondCommand());
        plugin.getCommand("rules").setExecutor(new RulesCommand());
        plugin.getCommand("skull").setExecutor(new SkullCommand());
        plugin.getCommand("setspawn").setExecutor(new SetspawnCommand());
        plugin.getCommand("spawn").setExecutor(new SpawnCommand());
        plugin.getCommand("spectate").setExecutor(new SpectateCommand());
        plugin.getCommand("tp").setExecutor(new TPCommand());
        plugin.getCommand("tphere").setExecutor(new TPHereCommand());
        plugin.getCommand("tpaccept").setExecutor(new TPAcceptCommand());
        plugin.getCommand("tpa").setExecutor(new TPACommand());
        plugin.getCommand("tpcancel").setExecutor(new TPCancelCommand());
        plugin.getCommand("tpdeny").setExecutor(new TPDenyCommand());
        plugin.getCommand("unban").setExecutor(new UnbanCommand());
        plugin.getCommand("vanish").setExecutor(new VanishCommand());
        plugin.getCommand("delwarp").setExecutor(new DelwarpCommand());
        plugin.getCommand("setwarp").setExecutor(new SetwarpCommand());
        plugin.getCommand("warp").setExecutor(new WarpCommand());
        plugin.getCommand("whisper").setExecutor(new WhisperCommand());
        plugin.getCommand("workbench").setExecutor(new WorkbenchCommand());
    }
}
