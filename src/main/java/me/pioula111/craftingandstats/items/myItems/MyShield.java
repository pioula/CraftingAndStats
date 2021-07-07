package me.pioula111.craftingandstats.items.myItems;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class MyShield extends MyItem {
    public MyShield() {
        this.type = toString();
    }

    public MyShield(ItemStack item) {
        if (item.hasItemMeta()) {
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            for (NamespacedKey key : pdc.getKeys()) {
                switch (key.getKey()) {
                    case "name":
                        name = pdc.get(key, PersistentDataType.STRING);
                        break;
                    case "swapped_item":
                        swappedItem = Material.getMaterial(pdc.get(key, PersistentDataType.STRING));
                        break;
                }
            }
        }
    }

    @Override
    public ItemStack makeItem(int amount) {
        return super.basicMakeItem(this, amount);
    }

    @Override
    public String toString() {
        return "shield";
    }

    @Override
    public String prettyToString() {
        return "Tarcza";
    }
}
