package me.pioula111.craftingandstats.crafting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandNowaReceptura implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandNowaReceptura(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Wpisz /nowareceptura <nazwa_fachu> <nazwa_craftingu> <nazwa_receptury>");
            return true;
        }
        return true;
    }
}
