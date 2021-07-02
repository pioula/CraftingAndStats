package me.pioula111.craftingandstats.harvestBlocks;

import me.pioula111.craftingandstats.harvestBlocks.harvestTools.HTool;
import me.pioula111.craftingandstats.harvestBlocks.json.HarvestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandBlocks implements CommandExecutor {
    private HarvestManager harvestManager;

    public CommandBlocks(HarvestManager harvestManager) {
        this.harvestManager = harvestManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Spróbuj /bloki <pickaxe/sickle/scythe/axe>");
            return true;
        }

        HTool tool = harvestManager.getTool(args[0]);
        if (tool == null) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego narzędzia! Spróbuj /bloki <pickaxe/sickle/scythe/axe>");
            return true;
        }

        tool.showBlocks(((Player) sender).getPlayer());
        return true;
    }
}
