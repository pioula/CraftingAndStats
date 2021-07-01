package me.pioula111.craftingandstats.items.myItems;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.items.properites.drinks.Effect;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;

public class MyDrink extends MyItem {
    private ArrayList<Effect> effects;
    private int drinkColor;

    public MyDrink(ItemStack item) {
        super(item);
        if (item.hasItemMeta()) {
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            for (NamespacedKey key : pdc.getKeys()) {
                switch (key.getKey()) {
                    case "name":
                        name = pdc.get(key, PersistentDataType.STRING);
                        break;
                    case "swapped_item":
                        swappedItem = Material.getMaterial(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "effects":
                        effects = Effect.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "drink_color":
                        drinkColor = pdc.get(key, PersistentDataType.INTEGER);
                        break;
                }
            }
        }
    }

    public MyDrink() {
        this.type = toString();
        effects = new ArrayList<>();
    }

    @Override
    public ItemStack makeItem(int amount) {
        ItemStack item = super.basicMakeItem(this, amount);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.lore(new ArrayList<>());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        for (Effect effect : effects) {
            meta.addCustomEffect(new PotionEffect(effect.getEffectType(), effect.getTime(), effect.getLevel()),true);
        }
        meta.setColor(Color.fromRGB(drinkColor));

        pdc.set(NameSpacedKeys.KEY_EFFECTS, PersistentDataType.STRING, effects.toString());
        pdc.set(NameSpacedKeys.KEY_DRINK_COLOR, PersistentDataType.INTEGER, drinkColor);

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public String toString() {
        return "drink";
    }

    @Override
    public String prettyToString() {
        return "Napój";
    }

    public void addEffect(Effect effect) {
        if (effects == null)
            effects = new ArrayList<>();

        effects.add(effect);
    }

    public void setDrinkColor(int drinkColor) {
        this.drinkColor = drinkColor;
    }
}
