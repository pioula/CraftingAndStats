package me.pioula111.craftingandstats;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropItemHelper {
    public static void dropItem(Player player, ItemStack itemStack) {
        player.getWorld().dropItem(player.getLocation(), itemStack);
    }
}
