package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.itemy.MyItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class Material {
    private MyItem material;
    private int amount;
    public Material(MyItem material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public MyItem getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return material.getNazwa().replace("_", " ") + " x" + amount;
    }
}
