package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.items.myItems.MyItem;

public class Ingredient {
    private MyItem ingredient;
    private int amount;
    public Ingredient(MyItem ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public MyItem getIngredient() {
        return ingredient;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return ingredient.getName() + " x" + amount;
    }
}
