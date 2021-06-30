package me.pioula111.craftingandstats.items.myItems;

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


public class MyWeapon extends MyItem {
    private Property weaponType;
    private double dmg;
    private Property statistic;
    private int statisticLevel;
    private Property addition;

    public MyWeapon(ItemStack item) {
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
                    case "weapon_type":
                        weaponType = Property.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "dmg":
                        dmg = pdc.get(key, PersistentDataType.DOUBLE);
                        break;
                    case "addition":
                        addition = Property.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "statistic":
                        statistic = Property.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "statistic_level":
                        statisticLevel = pdc.get(key, PersistentDataType.INTEGER);
                        break;
                }
            }
        }
    }

    public MyWeapon() {
        this.type = toString();
    }

    @Override
    public ItemStack makeItem(int amount) {
        ItemStack item = super.basicMakeItem(this, amount);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = item.lore();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        lore.add(Component.text().content(weaponType.prettyToString()).style(MyItem.LORE_COLOR).build());
        lore.add(Component.text().content("Wymagana " + statistic.prettyToString() + ": " + statisticLevel).style(MyItem.LORE_COLOR).build());
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(), "dmg", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        pdc.set(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING, weaponType.toString());
        pdc.set(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE, dmg);
        pdc.set(NameSpacedKeys.KEY_STATISTIC, PersistentDataType.STRING, statistic.toString());
        pdc.set(NameSpacedKeys.KEY_STATISTIC_LEVEL, PersistentDataType.INTEGER, statisticLevel);
        meta.setUnbreakable(true);

        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void setAddition(Property addition) {
        this.addition = addition;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public void setStatistic(Property statistic) {
        this.statistic = statistic;
    }

    public void setStatisticLevel(int statisticLevel) {
        this.statisticLevel = statisticLevel;
    }

    public void setWeaponType(Property weaponType) {
        this.weaponType = weaponType;
    }



    @Override
    public String toString() {
        return "weapon";
    }

    @Override
    public String prettyToString() {
        return "Broń";
    }
}
