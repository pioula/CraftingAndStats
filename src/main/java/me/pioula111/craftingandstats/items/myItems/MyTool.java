package me.pioula111.craftingandstats.items.myItems;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.items.properites.Property;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class MyTool extends MyItem {
    private Property toolType;

    public MyTool(ItemStack item) {
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
                    case "tool_type":
                        toolType = Property.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                }
            }
        }
    }

    public MyTool() {
        this.type = toString();
    }

    @Override
    public ItemStack makeItem(int amount) {
        ItemStack item = super.basicMakeItem(this, amount);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        pdc.set(NameSpacedKeys.KEY_TOOL_TYPE, PersistentDataType.STRING, toolType.toString());
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(), "dmg", 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public String toString() {
        return "tool";
    }

    @Override
    public String prettyToString() {
        return "Narzędzie";
    }

    public void setToolType(Property toolType) {
        this.toolType = toolType;
    }
}
