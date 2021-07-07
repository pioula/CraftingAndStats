package me.pioula111.craftingandstats.thievery.keyLocking;

import me.pioula111.craftingandstats.thievery.keyLocking.json.LootManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandChestLoot implements CommandExecutor {
    private LootManager lootManager;

    public CommandChestLoot(LootManager lootManager) {
        this.lootManager = lootManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Zła komenda!");
            return true;
        }

        lootManager.showLoots((Player) sender);
        return true;
    }
}
