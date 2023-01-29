package net.achymake.essential.files;

public class Files {
    public static void start(){
        ExperienceConfig.setup();
        KitConfig.setup();
        LocationConfig.setup();
        MotdConfig.setup();
        PlayerConfig.setup();
    }
    public static void reload(){
        Config.reload();
        ExperienceConfig.reload();
        KitConfig.reload();
        LocationConfig.reload();
        MotdConfig.reload();
        PlayerConfig.reload();
    }
}