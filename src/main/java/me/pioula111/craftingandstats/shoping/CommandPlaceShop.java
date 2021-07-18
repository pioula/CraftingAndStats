package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.markers.Marker;
import me.pioula111.craftingandstats.shoping.json.ShopManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPlaceShop implements CommandExecutor {
    private ShopManager shopManager;

    public CommandPlaceShop(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

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

        Entity shop = Marker.newMarker(2, (Player) sender, "Sklep");
        shopManager.addShop(shop);

        return true;
    }
}
