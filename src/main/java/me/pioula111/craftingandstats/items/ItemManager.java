package me.pioula111.craftingandstats.items;

import me.pioula111.craftingandstats.items.myItems.MyItem;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ItemManager {
    private HashMap<String, MyItem> makers;

    public ItemManager() {
        makers = new HashMap<>();
    }

    public boolean isMakingItem(Player maker) {
        return makers.containsKey(maker.getName());
    }

    public void updateMaker(Player maker, MyItem item) {
        makers.put(maker.getName(), item);
    }

    public void removeMaker(Player maker) {
        makers.remove(maker.getName());
    }

    public MyItem getItem(Player maker) {
        return makers.get(maker.getName());
    }
}
