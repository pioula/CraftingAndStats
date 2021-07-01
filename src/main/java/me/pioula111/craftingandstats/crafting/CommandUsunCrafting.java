package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.markers.Marker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandUsunCrafting implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandUsunCrafting(CraftingManager craftingManager) {
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

        if (!job.hasWorkBench(args[1])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego craftingu!");
            return true;
        }

        job.removeWorkBench(args[1]);
        WorkBench.removeWorkBenches(((Player) sender).getWorld(), args[1]);

        sender.sendMessage(ChatColor.GREEN + "Pomyślnie usunięto wszystkie craftingi!");
        return true;
    }
}
