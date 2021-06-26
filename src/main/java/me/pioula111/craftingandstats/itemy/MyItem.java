package me.pioula111.craftingandstats.itemy;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.itemy.bronie.TypBroni;
import me.pioula111.craftingandstats.itemy.rodzaje.Bron;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;
import me.pioula111.craftingandstats.itemy.statystyki.Statystyka;
import me.pioula111.craftingandstats.itemy.ulepszenia.Ulepszenie;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyItem {
    private String nazwa;
    private Material podmienionyItem;
    private RodzajItemu rodzaj;
    private TypBroni typBroni;
    private double dmg;
    private Statystyka wymaganaStatystyka;
    private int wielkoscStatystyki;
    private Ulepszenie ulepszenie;


    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setPodmienionyItem(Material podmienionyItem) {
        this.podmienionyItem = podmienionyItem;
    }

    public void setRodzaj(RodzajItemu rodzaj) {
        this.rodzaj = rodzaj;
    }

    public void setTypBroni(TypBroni typBroni) {
        this.typBroni = typBroni;
    }

    public ItemStack makeItem() {
        ItemStack item = new ItemStack(podmienionyItem, 1);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text().content(nazwa.replace("_"," ")).decoration(TextDecoration.ITALIC, false).build());
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text().content(rodzaj.prettyToString()).style(Style.style(TextColor.color(0x009999))).build());

        meta.getPersistentDataContainer().set(NameSpacedKeys.podmienionyItemKey, PersistentDataType.STRING, podmienionyItem.name());
        meta.getPersistentDataContainer().set(NameSpacedKeys.nazwaKey, PersistentDataType.STRING, nazwa);
        meta.getPersistentDataContainer().set(NameSpacedKeys.rodzajKey, PersistentDataType.STRING, rodzaj.toString());
        if (typBroni != null) {
            lore.add(Component.text().content(typBroni.prettyToString()).style(Style.style(TextColor.color(0x009999))).build());
            meta.getPersistentDataContainer().set(NameSpacedKeys.typBroniKey, PersistentDataType.STRING, typBroni.toString());

            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(), "dmg", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            meta.getPersistentDataContainer().set(NameSpacedKeys.dmgKey, PersistentDataType.DOUBLE, dmg);

            lore.add(Component.text().content("Wymagana " + wymaganaStatystyka.prettyToString() + ": " + wielkoscStatystyki).style(Style.style(TextColor.color(0x009999))).build());
            meta.getPersistentDataContainer().set(NameSpacedKeys.wymaganaStatystykaKey, PersistentDataType.STRING, wymaganaStatystyka.toString());
            meta.getPersistentDataContainer().set(NameSpacedKeys.wielkoscStatystykiKey, PersistentDataType.INTEGER, wielkoscStatystyki);

            if (!ulepszenie.toString().equals("brak")) {
                lore.add(Component.text().content("Ulepszenie: " + ulepszenie.prettyToString()).style(Style.style(TextColor.color(0x009999))).build());
            }
            meta.getPersistentDataContainer().set(NameSpacedKeys.ulepszenieKey, PersistentDataType.STRING, ulepszenie.toString());
        }


        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public void setStatystyka(Statystyka wymaganaStatystyka) {
        this.wymaganaStatystyka = wymaganaStatystyka;
    }

    public void setWielkoscStatystyki(int wielkoscStatystyki) {
        this.wielkoscStatystyki = wielkoscStatystyki;
    }

    public void setUlepszenie(Ulepszenie ulepszenie) {
        this.ulepszenie = ulepszenie;
    }
}
