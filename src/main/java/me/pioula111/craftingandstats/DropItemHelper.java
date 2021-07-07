package me.pioula111.craftingandstats;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropItemHelper {
    public static void dropItem(Player player, ItemStack itemStack) {
        Item boat = (Item) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.DROPPED_ITEM);
        boat.setCanPlayerPickup(true);
        boat.setItemStack(itemStack);
    }
}
