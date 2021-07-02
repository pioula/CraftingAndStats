package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.markers.Marker;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class CommandPlaceCrafting implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandPlaceCrafting(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nie jesteś graczem!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Niepoprawna komenda!");
            return true;
        }

        String craftingName = args[0].replace("_"," ");
        if (!(craftingManager.hasCrafting(craftingName))) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego craftingu!");
            return true;
        }


        Marker.newMarker(2, (Player) sender, craftingManager.getCrafting(craftingName).getName());

        return true;
    }
}
