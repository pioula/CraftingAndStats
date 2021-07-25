package me.pioula111.craftingandstats.lockedChests;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAddOwner implements CommandExecutor {
    private AddingOwnerManager addingOwnerManager;

    public CommandAddOwner(AddingOwnerManager addingOwnerManager) {
        this.addingOwnerManager = addingOwnerManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Zła komenda! Użyj /dodajwlasciciela <nick_gracza>");
            return true;
        }

        if (player.getServer().getPlayer(args[0]) == null) {
            player.sendMessage(ChatColor.RED + "Nie ma takiego gracza!");
            return true;
        }

        addingOwnerManager.addPlayer(player, args[0]);
        player.sendMessage(ChatColor.GREEN + "Zaczęto dodawać gracza " + args[0]);

        return true;
    }
}
