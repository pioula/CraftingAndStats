package me.pioula111.craftingandstats.thievery.keyLocking;

import me.pioula111.craftingandstats.items.myItems.MyItem;

public class LootItem {
    private MyItem item;
    private double chance;
    private int maxAmount;

    public LootItem(MyItem item, double chance, int amount) {
        this.item = item;
        this.chance = chance;
        this.maxAmount = amount;
    }

    public String toString() {
        return item.getName() + " " + chance + "%";
    }

    public MyItem getItem() {
        return item;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }
}
