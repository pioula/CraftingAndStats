package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandNewCrafting implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandNewCrafting(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Zła komenda, wpisz /nowycrafting <nazwa_fachu> <nazwa_craftingu>");
            return true;
        }
        String jobName = args[0].replace("_"," ");

        Job job;
        if (!craftingManager.hasJob(jobName)) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }
        job = craftingManager.getJob(jobName);

        String craftingName = args[1].replace("_"," ");

        if (craftingManager.hasCrafting(craftingName)) {
            sender.sendMessage(ChatColor.RED + "Jest już taki crafting!");
            return true;
        }


        Crafting newWorkbench = new Crafting(craftingName, job);
        job.addCrafting(newWorkbench);

        sender.sendMessage(ChatColor.GREEN + "Crafting został pomyślnie stworzony!");
        return true;
    }
}
