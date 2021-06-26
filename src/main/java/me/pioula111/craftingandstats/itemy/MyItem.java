package me.pioula111.craftingandstats.itemy;

import me.pioula111.craftingandstats.NameSpacedKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class MyItem {
    private String nazwa;
    private Material podmienionyItem;
    private RodzajItemu rodzaj;

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setPodmienionyItem(Material podmienionyItem) {
        this.podmienionyItem = podmienionyItem;
    }

    public void setRodzaj(RodzajItemu rodzaj) {
        this.rodzaj = rodzaj;
    }

    public ItemStack makeItem() {
        ItemStack item = new ItemStack(podmienionyItem, 1);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text().content(nazwa).decoration(TextDecoration.ITALIC, false).build());
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text().content(rodzaj.prettyToString()).style(Style.style(TextColor.color(0x009999))).build());
        meta.lore(lore);
        meta.getPersistentDataContainer().set(NameSpacedKeys.podmienionyItemKey, PersistentDataType.STRING, podmienionyItem.name());
        meta.getPersistentDataContainer().set(NameSpacedKeys.nazwaKey, PersistentDataType.STRING, nazwa.replace("_"," "));
        meta.getPersistentDataContainer().set(NameSpacedKeys.rodzajKey, PersistentDataType.STRING, rodzaj.toString());
        item.setItemMeta(meta);


        return item;
    }
}
