package me.pioula111.craftingandstats.items.myItems;

import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.items.properites.Property;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;

public class MyArmor extends MyItem {
    private Property addition;
    private int armor;

    public MyArmor(ItemStack item) {
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
                    case "addition":
                        addition = Property.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "armor":
                        armor = pdc.get(key, PersistentDataType.INTEGER);
                        break;
                }
            }
        }
    }

    public MyArmor() {
        super();
    }

    @Override
    public ItemStack makeItem(int amount) {
        ItemStack item = super.basicMakeItem(this, amount);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        List<Component> lore = item.lore();

        if (armor != 0) {
            lore.add(ComponentWrapper.lore("Ulepszenie: " + addition.prettyToString()));
            pdc.set(NameSpacedKeys.KEY_ADDITION, PersistentDataType.STRING, addition.toString());
        }


        meta.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        meta.setUnbreakable(true);

        pdc.set(NameSpacedKeys.KEY_ARMOR, PersistentDataType.INTEGER, armor);


        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void setAddition(Property addition) {
        this.addition = addition;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "armor";
    }

    @Override
    public String prettyToString() {
        return "Pancerz";
    }
}
