package me.pioula111.craftingandstats.items.commands;

import me.pioula111.craftingandstats.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsHelper {
    public static Player conditions(CommandSender sender, int argsLength, int argsProperLength, ItemManager itemManager) {
        if (!(sender instanceof Player))
            return null;

        if (argsLength != argsProperLength) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return null;
        }

        Player player = (Player) sender;

        if (!itemManager.isMakingItem(player)) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return null;
        }

        return player;
    }
}
