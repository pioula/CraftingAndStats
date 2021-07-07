package me.pioula111.craftingandstats.fishingAndBoats.fishing;

import me.pioula111.craftingandstats.markers.Marker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPlaceFishery implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nie jesteś graczem!");
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Niepoprawna komenda!");
            return true;
        }

        Marker.newMarker(2, (Player) sender, "Łowisko");

        return true;
    }
}
