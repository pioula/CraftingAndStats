package me.pioula111.craftingandstats;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemHelper {
    public static void dropItem(Player player, ItemStack itemStack) {
        if (itemStack == null || itemStack.getAmount() <= 0)
            return;
        player.getWorld().dropItem(player.getLocation(), itemStack);
    }

    public static boolean hasItems(Inventory inv, String itemName, int amount) {
        for (ItemStack item : inv) {
            if (isThisItem(item, itemName)) {
                amount -= item.getAmount();
            }
        }
        return amount <= 0;
    }

    private static boolean isThisItem(ItemStack item, String itemName) {
        return item != null && item.getAmount() > 0 && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                item.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(itemName);
    }

    public static void takeItems(Inventory inv, String itemName, int amount) {
        for (ItemStack item : inv) {
            if (isThisItem(item, itemName)) {
                int takenItems = Math.min(amount, item.getAmount());
                item.setAmount(item.getAmount() - takenItems);
                if (item.getAmount() <= 0) {
                    inv.remove(item);
                }
                amount -= takenItems;
                if (amount == 0)
                    break;
            }
        }
    }
}
