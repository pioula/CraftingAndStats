package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.ItemHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.myItems.MyOthers;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Price {
    private int gold, silver, bronze;
    public Price(int gold, int silver, int bronze) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }


    public int getBronze() {
        return bronze;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }

    public boolean hasThisMuch(Player player) {
        return ItemHelper.hasItems(player.getInventory(), "Złota Moneta", gold) &&
                ItemHelper.hasItems(player.getInventory(), "Srebrna Moneta", silver) &&
                ItemHelper.hasItems(player.getInventory(), "Miedziana Moneta", bronze);
    }

    public void addMoney(Price price) {
        gold += price.getGold();
        silver += price.getSilver();
        bronze += price.getBronze();
        silver += bronze/100;
        bronze %= 100;
        gold += silver / 100;
        silver %= 100;
    }

    public ItemStack getGoldItem() {
        MyItem goldItem = new MyOthers();
        goldItem.setName("Złota Moneta");
        goldItem.setSwappedItem(Material.SLIME_BALL);
        return goldItem.makeItem(gold);
    }

    public ItemStack getSilverItem() {
        MyItem silverItem = new MyOthers();
        silverItem.setName("Srebrna Moneta");
        silverItem.setSwappedItem(Material.SLIME_BALL);
        return silverItem.makeItem(silver);
    }

    public ItemStack getBronzeItem() {
        MyItem bronzeItem = new MyOthers();
        bronzeItem.setName("Miedziana Moneta");
        bronzeItem.setSwappedItem(Material.SLIME_BALL);
        return bronzeItem.makeItem(bronze);
    }
}
