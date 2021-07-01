package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.gui.MenuHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;

public class Recipe implements Comparable<Recipe> {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private Ingredient result;

    public Recipe(String name, ArrayList<Ingredient> ingredients, Ingredient result) {
        this.ingredients = ingredients;
        this.name = name;
        this.result = result;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.replace("_"," ");
    }

    public Ingredient getResult() {
        return result;
    }

    public Component menuComponent(int nr) {
        return Component.text().content("   " + nr + ". ").style(Style.style(MenuHelper.DECORATIONS)).append(Component.text()
                .content(this + "\n").style(Style.style(MenuHelper.RECIPE_NAME))).build();
    }

    @Override
    public int compareTo(@NotNull Recipe o) {
        return name.compareTo(o.getName());
    }
}
