package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.MenuHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Material> materials;
    private Material result;

    public Recipe(String name, ArrayList<Material> materials, Material result) {
        this.materials = materials;
        this.name = name;
        this.result = result;
    }

    public ArrayList<Material> getMaterials() {
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

    public Material getResult() {
        return result;
    }

    public Component menuComponent(int nr) {
        return Component.text().content("   " + nr + ". ").style(Style.style(MenuHelper.DECORATIONS)).append(Component.text()
                .content(this + "\n").style(Style.style(MenuHelper.RECIPE_NAME))).build();
    }
}
