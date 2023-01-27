package net.achymake.essential.files;

public class Files {
    public static void start(){
        KitConfig.setup();
        LocationConfig.setup();
        MotdConfig.setup();
        TablistConfig.setup();
    }
    public static void reload(){
        Config.reload();
        KitConfig.reload();
        LocationConfig.reload();
        MotdConfig.reload();
        PlayerConfig.reload();
        TablistConfig.reload();
    }
}