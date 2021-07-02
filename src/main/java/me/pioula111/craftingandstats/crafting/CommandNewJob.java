package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandNewJob implements CommandExecutor {
    private CraftingManager craftingManager;
    public CommandNewJob(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Spróbuj użyć /nowyfach <nazwa_fachu>");
            return true;
        }

        String jobName = args[0].replace("_"," ");

        if (craftingManager.hasJob(jobName)) {
            sender.sendMessage(ChatColor.RED + "Jest już taka praca!");
            return true;
        }

        craftingManager.addJob(new Job(jobName));
        sender.sendMessage(ChatColor.GREEN + "Dodano pomyślnie nowy fach!");
        return true;
    }
}
