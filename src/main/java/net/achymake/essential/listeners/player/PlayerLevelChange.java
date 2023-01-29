package net.achymake.essential.listeners.player;

import net.achymake.essential.Essential;
import net.achymake.essential.files.ExperienceConfig;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerLevelChange implements Listener {
    public PlayerLevelChange(Essential plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event){
        if (!event.getPlayer().hasPermission("essential.skills"))return;
        if (event.getPlayer().getLevel() > event.getOldLevel()){
            if (ExperienceConfig.get().getKeys(false).contains(String.valueOf(event.getPlayer().getLevel()))){
                event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ExperienceConfig.get().getDouble(event.getPlayer().getLevel()+".max-health"));
            }
            if (ExperienceConfig.get().getBoolean("settings.level-up.particle.enable")){
                event.getPlayer().spawnParticle(Particle.valueOf(ExperienceConfig.get().getString("settings.level-up.particle.type")),event.getPlayer().getLocation(), ExperienceConfig.get().getInt("settings.level-up.particle.count"), ExperienceConfig.get().getDouble("settings.level-up.particle.offsetX"), ExperienceConfig.get().getDouble("settings.level-up.particle.offsetY"), ExperienceConfig.get().getDouble("settings.level-up.particle.offsetZ"), 0);
            }
            if (ExperienceConfig.get().getBoolean("settings.level-up.sound.enable")){
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(ExperienceConfig.get().getString("settings.level-up.sound.type")), Float.valueOf(ExperienceConfig.get().getString("settings.level-up.sound.volume")), Float.valueOf(ExperienceConfig.get().getString("settings.level-up.sound.pitch")));
            }
        }
        if (event.getPlayer().getLevel() < event.getOldLevel()){
            if (ExperienceConfig.get().getKeys(false).contains(String.valueOf(event.getPlayer().getLevel()))){
                event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ExperienceConfig.get().getDouble(event.getPlayer().getLevel()+".max-health"));
            }
            if (ExperienceConfig.get().getBoolean("settings.level-up.particle.enable")){
                event.getPlayer().spawnParticle(Particle.valueOf(ExperienceConfig.get().getString("settings.level-down.particle.type")),event.getPlayer().getLocation(), ExperienceConfig.get().getInt("settings.level-down.particle.count"), ExperienceConfig.get().getDouble("settings.level-down.particle.offsetX"), ExperienceConfig.get().getDouble("settings.level-down.particle.offsetY"), ExperienceConfig.get().getDouble("settings.level-down.particle.offsetZ"), 0);
            }
            if (ExperienceConfig.get().getBoolean("settings.level-up.sound.enable")){
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(ExperienceConfig.get().getString("settings.level-down.sound.type")), Float.valueOf(ExperienceConfig.get().getString("settings.level-down.sound.volume")), Float.valueOf(ExperienceConfig.get().getString("settings.level-down.sound.pitch")));
            }
        }
    }
}
