package me.pioula111.craftingandstats.crafting;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;

public class Recipe {
    private String name;
    private HashSet<Material> materials;
    private ItemStack result;

    public Recipe(String name, HashSet<Material> materials, ItemStack result) {
        this.materials = materials;
        this.name = name;
        this.result = result;
    }

    public HashSet<Material> getMaterials() {
        return materials;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name.replace("_"," "));

        builder.append(": ");
        for (Material material : materials) {
            builder.append(material.toString()).append(", ");
        }

        return builder.toString();
    }

    public ItemStack getResult() {
        return result;
    }
}
