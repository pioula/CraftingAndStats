package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandRemoveCrafting implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandRemoveCrafting(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Użyj /usuncrafting <fach> <crafting>");
            return true;
        }

        if (!craftingManager.hasJob(args[0])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }
        Job job = craftingManager.getJob(args[0]);

        if (!job.hasCrafting(args[1])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego craftingu!");
            return true;
        }

        job.removeCrafting(args[1]);
        Crafting.removeWorkBenches(((Player) sender).getWorld(), args[1]);

        sender.sendMessage(ChatColor.GREEN + "Pomyślnie usunięto wszystkie craftingi!");
        return true;
    }
}
