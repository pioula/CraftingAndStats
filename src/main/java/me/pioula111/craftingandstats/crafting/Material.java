package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.NameSpacedKeys;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class Material {
    private ItemStack material;
    public Material(ItemStack material) {
        this.material = material;
    }

    public ItemStack getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        if (material.hasItemMeta() &&
        material.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.nazwaKey, PersistentDataType.STRING))
            return material.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.nazwaKey, PersistentDataType.STRING).replace("_", " ") + " x" + material.getAmount();
        else
            return material.getI18NDisplayName() + " x" + material.getAmount();
    }
}
