package me.pioula111.craftingandstats.crafting;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;

public class Recipe {
    private final static TextColor decorations = TextColor.color(0x2C3394);
    private final static TextColor mainName = TextColor.color(0x8088FF);
    private final static TextColor recipeName = TextColor.color(0x947B1E);
    private final static TextColor LPMcolor = TextColor.color(0xDECA1B);
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

    public Component menuComponent(int nr) {
        return Component.text().content("   " + nr + ". ").style(Style.style(decorations)).append(Component.text()
                .content(this + "\n").style(Style.style(recipeName))).build();
    }
}
