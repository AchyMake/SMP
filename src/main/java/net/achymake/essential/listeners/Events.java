package net.achymake.essential.listeners;

import net.achymake.essential.Essential;
import net.achymake.essential.listeners.block.BlockBreakNotify;
import net.achymake.essential.listeners.block.BlockPlaceNotify;
import net.achymake.essential.listeners.bucket.BucketEmptyNotify;
import net.achymake.essential.listeners.chat.PlayerChat;
import net.achymake.essential.listeners.chat.PlayerChatColor;
import net.achymake.essential.listeners.chat.PlayerChatMuted;
import net.achymake.essential.listeners.chat.PlayerCommandPreprocess;
import net.achymake.essential.listeners.connection.*;
import net.achymake.essential.listeners.entity.PlayerDeath;
import net.achymake.essential.listeners.entity.PlayerRespawn;
import net.achymake.essential.listeners.move.PlayerMoveFrozen;
import net.achymake.essential.listeners.move.PlayerMoveVanished;
import net.achymake.essential.listeners.pvp.PlayerDamagePlayer;
import net.achymake.essential.listeners.pvp.PlayerDamagePlayerByArrow;
import net.achymake.essential.listeners.pvp.PlayerDamagePlayerBySnowball;
import net.achymake.essential.listeners.pvp.PlayerDamagePlayerBySpectralArrow;

public class Events {
    public static void start(Essential plugin){
        new BlockBreakNotify(plugin);
        new BlockPlaceNotify(plugin);
        new BucketEmptyNotify(plugin);
        new PlayerChat(plugin);
        new PlayerChatColor(plugin);
        new PlayerChatMuted(plugin);
        new PlayerCommandPreprocess(plugin);
        new PlayerJoinNotifyUpdate(plugin);
        new PlayerJoinSetup(plugin);
        new PlayerJoinVanished(plugin);
        new PlayerLoginSetup(plugin);
        new PlayerQuitVanished(plugin);
        new PlayerQuitWithTPATask(plugin);
        new PlayerDeath(plugin);
        new PlayerRespawn(plugin);
        new PlayerMoveFrozen(plugin);
        new PlayerMoveVanished(plugin);
        new PlayerDamagePlayer(plugin);
        new PlayerDamagePlayerByArrow(plugin);
        new PlayerDamagePlayerBySnowball(plugin);
        new PlayerDamagePlayerBySpectralArrow(plugin);
    }
}