package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
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
            sender.sendMessage(ChatColor.RED + "Zła komenda, wpisz /nowycrafting <nazwa_fachu> <nazwa_craftingu>");
            return true;
        }

        Job job;
        if (!craftingManager.hasJob(args[0])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }
        job = craftingManager.getJob(args[0]);

        if (craftingManager.hasCrafting(args[1])) {
            sender.sendMessage(ChatColor.RED + "Jest już taki crafting!");
            return true;
        }


        WorkBench newWorkbench = new WorkBench(args[0], job);
        job.addWorkbench(newWorkbench);

        sender.sendMessage(ChatColor.GREEN + "Crafting został pomyślnie stworzony!");
        return true;
    }
}
