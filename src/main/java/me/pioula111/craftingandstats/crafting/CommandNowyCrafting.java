package me.pioula111.craftingandstats.crafting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandNowyCrafting implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandNowyCrafting(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Zła komenda, wpisz /nowycrafting <nazwa_craftingu> <nazwa_fachu>");
            return true;
        }

        Job job;

        if (craftingManager.hasJob(args[1])) {
            job = craftingManager.getJob(args[1]);
        }
        else {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }

        if (craftingManager.hasCrafting(args[1],job)) {
            sender.sendMessage(ChatColor.RED + "Jest już taki crafting!");
            return true;
        }


        WorkBench newWorkbench = new WorkBench(args[0], job);
        craftingManager.addNewWorkbench(newWorkbench);
        return true;
    }
}
