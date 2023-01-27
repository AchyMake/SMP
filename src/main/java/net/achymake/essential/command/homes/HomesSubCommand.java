package net.achymake.essential.command.homes;

import org.bukkit.entity.Player;

public abstract class HomesSubCommand {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract void perform(Player player, String args[]);
}