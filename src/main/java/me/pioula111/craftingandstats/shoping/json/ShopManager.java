package me.pioula111.craftingandstats.shoping.json;

import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.shoping.Good;
import me.pioula111.craftingandstats.shoping.MyBoolean;
import me.pioula111.craftingandstats.shoping.Price;
import me.pioula111.craftingandstats.shoping.Shop;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ShopManager {
    private HashMap<String, Shop> shops;

    public ShopManager() {
        this.shops = new HashMap<>();
    }

    public boolean hasShop(Entity shop) {
        return shops.containsKey(shop.getUniqueId().toString());
    }

    public Shop getShop(Entity shop) {
        return shops.get(shop.getUniqueId().toString());
    }

    public void removeShop(Entity shop) {
        shops.remove(shop.getUniqueId().toString());
    }

    public void addShop(Entity shop) {
        shops.put(shop.getUniqueId().toString(), new Shop());
    }

    public boolean playerHasShop(Player player) {
        MyBoolean bool = new MyBoolean();
        shops.forEach((key, value) -> {
            if (value.getOwner() != null && value.getOwner().equals(player.getName()))
                bool.setValue(true);
        });

        return bool.getValue();
    }

    public void addGood(Player player, ItemStack item, Price price) {
        shops.forEach((key, value) -> {
            if (value.getOwner() != null && value.getOwner().equals(player.getName()))
                value.getGoods().add(new Good(MyItem.fromItemStack(item), price,  item.getAmount()));
        });
    }
}
