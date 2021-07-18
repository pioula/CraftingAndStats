package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.items.myItems.MyItem;

public class Stock {
    private MyItem item;
    private int amount;

    public Stock(MyItem item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public MyItem getItem() {
        return item;
    }

    public void setItem(MyItem item) {
        this.item = item;
    }
}
