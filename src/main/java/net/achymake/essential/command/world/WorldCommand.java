package net.achymake.essential.command.world;

import net.achymake.essential.command.world.sub.*;
import net.achymake.essential.files.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldCommand implements CommandExecutor, TabCompleter {
    private final ArrayList<WorldSubCommand> worldSubCommands = new ArrayList<>();

    public WorldCommand(){
        worldSubCommands.add(new AddCommand());
        worldSubCommands.add(new CancelEntityCommand());
        worldSubCommands.add(new CancelSpawnCommand());
        worldSubCommands.add(new CreateCommand());
        worldSubCommands.add(new EditCommand());
        worldSubCommands.add(new GameruleCommand());
        worldSubCommands.add(new LavaFlowCommand());
        worldSubCommands.add(new RemoveCommand());
        worldSubCommands.add(new SetWorldSpawnCommand());
        worldSubCommands.add(new TeleportCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length > 0){
                Player player = (Player) sender;
                for (WorldSubCommand commands : getSubCommands()){
                    if (args[0].equals(commands.getName())){
                        commands.perform(player,args);
                    }
                }
            }
        }
        return true;
    }
    public ArrayList<WorldSubCommand> getSubCommands(){
        return worldSubCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (WorldSubCommand commandValue : getSubCommands()){
                commands.add(commandValue.getName());
            }
            return commands;
        }else if (args.length == 2){
            for (World world : Bukkit.getWorlds()){
                commands.add(world.getName());
            }
            return commands;
        }else if (args.length == 3){
            if (args[0].equalsIgnoreCase("add")){
                for (World.Environment environment : World.Environment.values()){
                    commands.add(environment.toString().toLowerCase());
                }
                return commands;
            }else if (args[0].equalsIgnoreCase("create")){
                for (World.Environment environment : World.Environment.values()){
                    commands.add(environment.toString().toLowerCase());
                }
                return commands;
            } else if (args[0].equalsIgnoreCase("gamerule")){
                for (String gameRule : Bukkit.getWorld(args[1]).getGameRules()){
                    commands.add(gameRule);
                }
                return commands;
            }else if (args[0].equalsIgnoreCase("lava-flow")) {
                commands.add(String.valueOf(WorldConfig.get().getBoolean(args[1]+".lava-flow")));
                return commands;
            }else if (args[0].equalsIgnoreCase("cancel-entity")) {
                for (EntityType entityType : EntityType.values()){
                    commands.add(entityType.toString().toLowerCase());
                }
                return commands;
            }else if (args[0].equalsIgnoreCase("cancel-spawn")) {
                for (EntityType entityType : EntityType.values()){
                    commands.add(entityType.toString().toLowerCase());
                }
                return commands;
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("gamerule")){
                commands.add(Bukkit.getWorld(args[1]).getGameRuleValue(args[2]));
                return commands;
            }else if (args[0].equalsIgnoreCase("cancel-entity")){
                commands.add(String.valueOf(WorldConfig.get().getBoolean(args[1]+".cancel-entity."+args[2].toUpperCase())));
                return commands;
            }else if (args[0].equalsIgnoreCase("cancel-spawn")){
                commands.add(String.valueOf(WorldConfig.get().getBoolean(args[1]+".cancel-spawn."+args[2].toUpperCase())));
                return commands;
            }
        }
        return commands;
    }
}