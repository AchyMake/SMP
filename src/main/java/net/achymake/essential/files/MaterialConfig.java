package net.achymake.essential.files;

import net.achymake.essential.Essential;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MaterialConfig {
    public static File configFile = new File(Essential.instance.getDataFolder(), "kit.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        get().addDefault("ACACIA_BOAT","Acacia Boat");
        get().addDefault("ACACIA_BUTTON","Acacia Button");
        get().addDefault("ACACIA_CHEST_BOAT","Acacia Chest Boat");
        get().addDefault("ACACIA_DOOR","Acacia Door");
        get().addDefault("ACACIA_FENCE","Acacia Fence");
        get().addDefault("ACACIA_FENCE_GATE","Acacia Fence Gate");
        get().addDefault("ACACIA_HANGING_SIGN","Acacia Hanging Sign");
        get().addDefault("ACACIA_LEAVES","Acacia Leaves");
        get().addDefault("ACACIA_LOG","Acacia Log");
        get().addDefault("ACACIA_PLANKS","Acacia Planks");
        get().addDefault("ACACIA_PRESSURE_PLATE","Acacia Pressure Plate");
        get().addDefault("ACACIA_SAPLING","Acacia Sapling");
        get().addDefault("ACACIA_SIGN","Acacia Sign");
        get().addDefault("ACACIA_SLAB","Acacia Slab");
        get().addDefault("ACACIA_STAIRS","Acacia Stairs");
        get().addDefault("ACACIA_TRAPDOOR","ACACIA_TRAPDOOR");
        get().addDefault("ACACIA_WALL_HANGING_SIGN","Acacia Wall Hanging Sign");
        get().addDefault("ACACIA_WALL_SIGN","Acacia Wall Sign");
        get().addDefault("ACACIA_WOOD","Acacia Wood");
        get().addDefault("ACTIVATOR_RAIL","ACTIVATOR_RAIL");
        get().addDefault("AIR","Air");
        get().addDefault("ALLAY_SPAWN_EGG","ALLAY_SPAWN_EGG");
        get().addDefault("ALLIUM","Allium");
        get().addDefault("AMETHYST_BLOCK","Amethyst Block");
        get().addDefault("AMETHYST_CLUSTER","Amethyst Cluster");
        get().addDefault("AMETHYST_SHARD","Amethyst Shard");
        get().addDefault("ANCIENT_DEBRIS","Ancient Debris");
        get().addDefault("ANDESITE","Andesite");
        get().addDefault("ANDESITE_SLAB","Andesite Slab");
        get().addDefault("ANDESITE_STAIRS","Andesite Stairs");
        get().addDefault("ANDESITE_WALL","Andesite Wall");
        get().addDefault("ANVIL","Anvil");
        get().addDefault("APPLE","Apple");
        get().addDefault("ARMOR_STAND","Armor Stand");
        get().addDefault("ARROW","ARROW");
        get().addDefault("ATTACHED_MELON_STEM","Attached Melon Stem");
        get().addDefault("ATTACHED_PUMPKIN_STEM","Attached Pumpkin Stem");
        get().addDefault("AXOLOTL_BUCKET","Axolotl Bucket");
        get().addDefault("AXOLOTL_SPAWN_EGG","Axolotl Spawn Egg");
        get().addDefault("AZALEA","Azalea");
        get().addDefault("AZALEA_LEAVES","Azalea Leaves");
        get().addDefault("AZURE_BLUET","Azure Bluet");
        get().addDefault("BAKED_POTATO","Baked Potato");
        get().addDefault("BAMBOO","Bamboo");
        get().addDefault("BAMBOO_BLOCK","Bamboo Block");
        get().addDefault("BAMBOO_BUTTON","Bamboo Button");
        get().addDefault("BAMBOO_CHEST_RAFT","Bamboo Chest Raft");
        get().addDefault("BAMBOO_DOOR","Bamboo Door");
        get().addDefault("BAMBOO_FENCE","Bamboo Fence");
        get().addDefault("BAMBOO_FENCE_GATE","Bamboo Fence Gate");
        get().addDefault("BAMBOO_HANGING_SIGN","Bamboo Hanging Sign");
        get().addDefault("BAMBOO_MOSAIC","Bamboo Mosaic");
        get().addDefault("BAMBOO_MOSAIC_SLAB","Bamboo Mosaic Slab");
        get().addDefault("BAMBOO_MOSAIC_STAIRS","Bamboo Mosaic Stairs");
        get().addDefault("BAMBOO_PLANKS","Bamboo Planks");
        get().addDefault("BAMBOO_PRESSURE_PLATE","Bamboo Pressure Plate");
        get().addDefault("BAMBOO_RAFT","Bamboo Raft");
        get().addDefault("BAMBOO_SIGN","Bamboo Sign");
        get().addDefault("BAMBOO_SLAB","Bamboo Slab");
        get().addDefault("BAMBOO_STAIRS","Bamboo Stairs");
        get().addDefault("BAMBOO_TRAPDOOR","Bamboo Trapdoor");
        get().addDefault("BAMBOO_WALL_HANGING_SIGN","Bamboo Wall Hanging Sign");
        get().addDefault("BAMBOO_WALL_SIGN","Bamboo Wall Sign");
        get().addDefault("BARREL","Barrel");
        get().addDefault("BARRIER","Barrier");
        get().addDefault("BASALT","Basalt");
        get().addDefault("BAT_SPAWN_EGG","Bat Spawn Egg");
        get().addDefault("BEACON","Beacon");
        get().addDefault("BEDROCK","Bedrock");
        get().addDefault("BEE_NEST","Bee Nest");
        get().addDefault("BEE_SPAWN_EGG","Bee Spawn Egg");
        get().addDefault("BEEF","Beef");
        get().addDefault("BEEHIVE","Beehive");
        get().addDefault("BEETROOT","Beetroot");
        get().addDefault("BEETROOT_SEEDS","Beetroot Seeds");
        get().addDefault("BEETROOT_SOUP","Beetroot Soup");
        get().addDefault("BEETROOTS","Beetroots");
        get().addDefault("BELL","Bell");
        get().addDefault("BIG_DRIPLEAF","Big Dripleaf");
        get().addDefault("BIG_DRIPLEAF_STEM","Big Dripleaf Stem");
        get().addDefault("BIRCH_BOAT","Birch Boat");
        get().addDefault("BIRCH_BUTTON","Birch Button");
        get().addDefault("BIRCH_CHEST_BOAT","Birch Chest Boat");
        get().addDefault("BIRCH_DOOR","Birch Door");
        get().addDefault("BIRCH_FENCE","Birch Fence");
        get().addDefault("BIRCH_FENCE_GATE","Birch Fence Gate");
        get().addDefault("BIRCH_HANGING_SIGN","Birch Hanging Sign");
        get().addDefault("BIRCH_LEAVES","Birch Leaves");
        get().addDefault("BIRCH_LOG","Birch Log");
        get().addDefault("BIRCH_PLANKS","Birch Planks");
        get().addDefault("BIRCH_PRESSURE_PLATE","Birch Pressure Plate");
        get().addDefault("BIRCH_SAPLING","Birch Sapling");
        get().addDefault("BIRCH_SIGN","Birch Sign");
        get().addDefault("BIRCH_SLAB","Birch Slab");
        get().addDefault("BIRCH_STAIRS","Birch Stairs");
        get().addDefault("BIRCH_TRAPDOOR","Birch Trapdoor");
        get().addDefault("BIRCH_WALL_HANGING_SIGN","Birch Wall Hanging Sign");
        get().addDefault("BIRCH_WALL_SIGN","Birch Wall Sign");
        get().addDefault("BIRCH_WOOD","Birch Wood");
        get().addDefault("BLACK_BANNER","Black Banner");
        get().addDefault("BLACK_BED","Black Bed");
        get().addDefault("BLACK_CANDLE","Black Candle");
        get().addDefault("BLACK_CANDLE_CAKE","Black Candle Cake");
        get().addDefault("BLACK_CARPET","Black Carpet");
        get().addDefault("BLACK_CONCRETE","Black Concrete");
        get().addDefault("BLACK_CONCRETE_POWDER","Black Concrete Powder");
        get().addDefault("BLACK_DYE","Black Dye");
        get().addDefault("BLACK_GLAZED_TERRACOTTA","Black Glazed Terracotta");
        get().addDefault("BLACK_SHULKER_BOX","Black Shulker Box");
        get().addDefault("BLACK_STAINED_GLASS","Black Stained Glass");
        get().addDefault("BLACK_STAINED_GLASS_PANE","BLACK_STAINED_GLASS_PANE");
        get().addDefault("BLACK_TERRACOTTA","Black Terracotta");
        get().addDefault("BLACK_WALL_BANNER","Black Wall Banner");
        get().addDefault("BLACK_WOOL","Black Wool");
        get().addDefault("BLACKSTONE","Blackstone");
        get().addDefault("BLACKSTONE_SLAB","Blackstone Slab");
        get().addDefault("BLACKSTONE_STAIRS","Blackstone Stairs");
        get().addDefault("BLACKSTONE_WALL","Blackstone Wall");
        get().addDefault("BLAST_FURNACE","Blast Furnace");
        get().addDefault("BLAZE_POWDER","Blaze Powder");
        get().addDefault("BLAZE_ROD","Blaze Rod");
        get().addDefault("BLAZE_SPAWN_EGG","Blaze Spawn Egg");
        get().addDefault("BLUE_BANNER","Blue Banner");
        get().addDefault("BLUE_BED","Blue Bed");
        get().addDefault("","");
        get().options().copyDefaults(true);
        save();
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