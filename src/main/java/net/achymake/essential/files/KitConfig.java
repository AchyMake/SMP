package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KitConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "kit.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        if (!configFile.exists()){
            List<String> lore = new ArrayList<>();
            lore.add("&9from");
            lore.add("&7-&6 Starter");
            get().addDefault("starter.cooldown",3600);
            get().addDefault("starter.materials.item-1.type","STONE_SWORD");
            get().addDefault("starter.materials.item-1.amount",1);
            get().addDefault("starter.materials.item-1.display.name","&fStone Sword");
            get().addDefault("starter.materials.item-1.display.lore",lore);
            get().addDefault("starter.materials.item-1.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-1.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-2.type","STONE_PICKAXE");
            get().addDefault("starter.materials.item-2.amount",1);
            get().addDefault("starter.materials.item-2.display.name","&fStone Pickaxe");
            get().addDefault("starter.materials.item-2.display.lore",lore);
            get().addDefault("starter.materials.item-2.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-2.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-3.type","STONE_AXE");
            get().addDefault("starter.materials.item-3.amount",1);
            get().addDefault("starter.materials.item-3.display.name","&fStone Axe");
            get().addDefault("starter.materials.item-3.display.lore",lore);
            get().addDefault("starter.materials.item-3.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-3.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-4.type","STONE_SHOVEL");
            get().addDefault("starter.materials.item-4.amount",1);
            get().addDefault("starter.materials.item-4.display.name","&fStone Shovel");
            get().addDefault("starter.materials.item-4.display.lore",lore);
            get().addDefault("starter.materials.item-4.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-4.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-5.type","STONE_HOE");
            get().addDefault("starter.materials.item-5.amount",1);
            get().addDefault("starter.materials.item-5.display.name","&fStone Hoe");
            get().addDefault("starter.materials.item-5.display.lore",lore);
            get().addDefault("starter.materials.item-5.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-5.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-6.type","LEATHER_HELMET");
            get().addDefault("starter.materials.item-6.amount",1);
            get().addDefault("starter.materials.item-6.display.name","&fLeather Helmet");
            get().addDefault("starter.materials.item-6.display.lore",lore);
            get().addDefault("starter.materials.item-6.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-6.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-7.type","LEATHER_CHESTPLATE");
            get().addDefault("starter.materials.item-7.amount",1);
            get().addDefault("starter.materials.item-7.display.name","&fLeather Chestplate");
            get().addDefault("starter.materials.item-7.display.lore",lore);
            get().addDefault("starter.materials.item-7.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-7.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-8.type","LEATHER_LEGGINGS");
            get().addDefault("starter.materials.item-8.amount",1);
            get().addDefault("starter.materials.item-8.display.name","&fLeather Leggings");
            get().addDefault("starter.materials.item-8.display.lore",lore);
            get().addDefault("starter.materials.item-8.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-8.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-9.type","LEATHER_BOOTS");
            get().addDefault("starter.materials.item-9.amount",1);
            get().addDefault("starter.materials.item-9.display.name","&fLeather Boots");
            get().addDefault("starter.materials.item-9.display.lore",lore);
            get().addDefault("starter.materials.item-9.enchantments.enchant-1.type","DURABILITY");
            get().addDefault("starter.materials.item-9.enchantments.enchant-1.amount",1);
            get().addDefault("starter.materials.item-10.type","COOKED_BEEF");
            get().addDefault("starter.materials.item-10.amount",16);
            get().addDefault("starter.materials.item-10.display.name","&fCooked Beef");
            get().addDefault("food.materials.item-1.type","COOKED_BEEF");
            get().addDefault("food.materials.item-1.amount",16);
            get().addDefault("food.materials.item-1.display.name","&fCooked Beef");
            get().options().copyDefaults(true);
            save();
        }
    }
    public static void save(){
        try {
            config.save(configFile);
        } catch (IOException e) {
            Essential.instance.sendMessage(e.getMessage());
        }
    }
    public static FileConfiguration get(){
        return config;
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}