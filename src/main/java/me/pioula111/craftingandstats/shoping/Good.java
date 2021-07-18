package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.items.myItems.MyItem;

public class Good {
    private MyItem product;
    private Price price;
    private int amount;

    public Good(MyItem product, Price price, int amount) {
        this.price = price;
        this.product = product;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MyItem getProduct() {
        return product;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setProduct(MyItem product) {
        this.product = product;
    }
}
