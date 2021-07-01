package me.pioula111.craftingandstats.items.myItems;

import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public abstract class MyItem {
    protected String name;
    protected Material swappedItem;
    protected String type;


    public MyItem() {
        this.type = this.toString();
    }

    public MyItem(ItemStack item) {
        this.type = this.toString();
    }

    public static MyItem fromItemStack(ItemStack item) {
        if (item.hasItemMeta()) {
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            if (!pdc.has(NameSpacedKeys.KEY_TYPE, PersistentDataType.STRING))
                return null;

            switch (pdc.get(NameSpacedKeys.KEY_TYPE, PersistentDataType.STRING)) {
                case "tool":
                    return new MyTool(item);
                case "armor":
                    return new MyArmor(item);
                case "food":
                    return new MyFood(item);
                case "handcraft":
                    return new MyHandCraft(item);
                case "others":
                    return new MyOthers(item);
                case "drink":
                    return new MyDrink(item);
                case "weapon":
                    return new MyWeapon(item);
            }
        }

        return null;
    }

    public String getType() {
        return type;
    }

    public abstract ItemStack makeItem(int amount);

    public void setName(String name) {
        this.name = name;
    }

    public void setSwappedItem(Material swappedItem) {
        this.swappedItem = swappedItem;
    }

    @Override
    public abstract String toString();

    public abstract String prettyToString();

    protected ItemStack basicMakeItem(MyItem type, int amount) {
        ItemStack item = new ItemStack(swappedItem, amount);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        List<Component> lore = new ArrayList<>();

        lore.add(ComponentWrapper.lore(type.prettyToString()));

        meta.displayName(ComponentWrapper.itemName(name));

        pdc.set(NameSpacedKeys.KEY_SWAPPED_ITEM, PersistentDataType.STRING, swappedItem.name());
        pdc.set(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING, name);
        pdc.set(NameSpacedKeys.KEY_TYPE, PersistentDataType.STRING, type.toString());
        pdc.set(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING, "pioula111");

        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public String getName() {
        return name;
    }
}
