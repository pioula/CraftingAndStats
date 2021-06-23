package me.pioula111.craftingandstats.crafting;

import org.bukkit.inventory.ItemStack;

public class Material {
    private ItemStack material;
    public Material(ItemStack material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return material.getI18NDisplayName() + " x" + material.getAmount();
    }
}
