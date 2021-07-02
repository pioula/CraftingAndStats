package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandRemoveJob implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandRemoveJob(CraftingManager craftingManager) {
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

        String jobName = args[0].replace("_"," ");
        if (!craftingManager.hasJob(jobName)) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }
        Job job = craftingManager.getJob(jobName);
        craftingManager.removeJob(jobName);
        for (Crafting crafting : job.getCraftings()) {
            Crafting.removeWorkBenches(((Player) sender).getWorld(), crafting.getName());
        }

        sender.sendMessage(ChatColor.GREEN + "Fach został pomyślnie usunięty!");
        return true;
    }
}
