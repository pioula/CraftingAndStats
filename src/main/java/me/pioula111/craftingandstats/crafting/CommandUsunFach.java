package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandUsunFach implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandUsunFach(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Użyj /usunfach <nazwa_fachu>");
            return true;
        }

        if (!craftingManager.hasJob(args[0])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }
        Job job = craftingManager.getJob(args[0]);
        craftingManager.removeJob(args[0]);
        for (WorkBench workBench : job.getWorkBenches()) {
            WorkBench.removeWorkBenches(((Player) sender).getWorld(), workBench.getName());
        }

        sender.sendMessage(ChatColor.GREEN + "Fach został pomyślnie usunięty!");
        return true;
    }
}
