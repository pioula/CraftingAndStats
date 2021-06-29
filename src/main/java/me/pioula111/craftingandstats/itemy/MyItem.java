package me.pioula111.craftingandstats.itemy;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.itemy.wlasciwosci.Wlasciwosc;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.TypBroni;
import me.pioula111.craftingandstats.itemy.wlasciwosci.napoje.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.narzedzia.TypNarzedzia;
import me.pioula111.craftingandstats.itemy.wlasciwosci.rodzaje.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.statystyki.Statystyka;
import me.pioula111.craftingandstats.itemy.wlasciwosci.ulepszenia.Ulepszenie;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyItem {
    private String nazwa;
    private Material podmienionyItem;
    private Wlasciwosc rodzaj;
    private Wlasciwosc typBroni;
    private double dmg;
    private Wlasciwosc wymaganaStatystyka;
    private int wielkoscStatystyki;
    private Wlasciwosc ulepszenie;
    private int obrona;
    private Wlasciwosc typNarzedzia;
    private ArrayList<Efekt> efekty;
    private int kolorNapoju;

    public MyItem() {}

    public MyItem(ItemStack item) {
        if (item.hasItemMeta()) {
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            for (NamespacedKey key : pdc.getKeys()) {
                switch (key.getKey()) {
                    case "nazwa":
                        nazwa = pdc.get(key, PersistentDataType.STRING);
                        break;
                    case "rodzaj":
                        rodzaj = Wlasciwosc.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "podmienionyitem":
                        podmienionyItem = Material.getMaterial(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "typbroni":
                        typBroni = Wlasciwosc.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "dmg":
                        dmg = pdc.get(key, PersistentDataType.DOUBLE);
                        break;
                    case "ulepszenie":
                        ulepszenie = Wlasciwosc.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "wymaganastatystyka":
                        wymaganaStatystyka = Wlasciwosc.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "wielkoscstatystyki":
                        wielkoscStatystyki = pdc.get(key, PersistentDataType.INTEGER);
                        break;
                    case "obrona":
                        obrona = pdc.get(key, PersistentDataType.INTEGER);
                        break;
                    case "typnarzedzia":
                        typNarzedzia = Wlasciwosc.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "efekty":
                        efekty = Efekt.deserialize(pdc.get(key, PersistentDataType.STRING));
                        break;
                    case "kolornapoju":
                        kolorNapoju = pdc.get(key, PersistentDataType.INTEGER);
                        break;
                    case "autor":
                        break;
                }
            }
        }
    }

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

    public ItemStack makeItem(int amount) {
        if (rodzaj instanceof Napoj) {
            ItemStack item = new ItemStack(podmienionyItem, amount);

            PotionMeta pMeta = (PotionMeta) item.getItemMeta();
            pMeta.getPersistentDataContainer().set(NameSpacedKeys.podmienionyItemKey, PersistentDataType.STRING, podmienionyItem.name());
            pMeta.getPersistentDataContainer().set(NameSpacedKeys.rodzajKey, PersistentDataType.STRING, rodzaj.toString());

            for (Efekt efekt : efekty) {
                pMeta.addCustomEffect(new PotionEffect(efekt.getTypEfektu(), efekt.getCzasTrwania(), efekt.getMoc()), true);
            }

            pMeta.getPersistentDataContainer().set(NameSpacedKeys.efektyKey, PersistentDataType.STRING, efekty.toString());
            pMeta.setColor(Color.fromBGR(kolorNapoju));
            pMeta.getPersistentDataContainer().set(NameSpacedKeys.kolorNapojuKey, PersistentDataType.INTEGER, kolorNapoju);
            pMeta.displayName(Component.text().content(nazwa.replace("_"," ")).decoration(TextDecoration.ITALIC, false).build());
            pMeta.getPersistentDataContainer().set(NameSpacedKeys.nazwaKey, PersistentDataType.STRING, nazwa);
            pMeta.getPersistentDataContainer().set(NameSpacedKeys.autorKey, PersistentDataType.STRING, "pioula111");

            item.setItemMeta(pMeta);
            return item;
        }
        else {
            ItemStack item = new ItemStack(podmienionyItem, amount);
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
                meta.setUnbreakable(true);
            }

            if (ulepszenie != null) {
                if (!ulepszenie.toString().equals("brak")) {
                    lore.add(Component.text().content("Ulepszenie: " + ulepszenie.prettyToString()).style(Style.style(TextColor.color(0x009999))).build());
                }
                meta.getPersistentDataContainer().set(NameSpacedKeys.ulepszenieKey, PersistentDataType.STRING, ulepszenie.toString());
            }

            if (rodzaj instanceof Pancerz) {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(UUID.randomUUID(), "armor", obrona, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
                meta.getPersistentDataContainer().set(NameSpacedKeys.obronaKey, PersistentDataType.INTEGER, obrona);
                meta.setUnbreakable(true);
            }

            if (rodzaj instanceof Narzedzia) {
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(), "dmg", 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                meta.getPersistentDataContainer().set(NameSpacedKeys.typNarzedziaKey, PersistentDataType.STRING, typNarzedzia.toString());
            }

            meta.lore(lore);
            meta.getPersistentDataContainer().set(NameSpacedKeys.autorKey, PersistentDataType.STRING, "pioula111");
            item.setItemMeta(meta);
            return item;
        }
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

    public void setObrona(int obrona) {
        this.obrona = obrona;
    }

    public void setTypNarzedzia(TypNarzedzia typNarzedzia) {
        this.typNarzedzia = typNarzedzia;
    }

    public void addEfekt(Efekt efekt) {
        if (efekty == null)
            efekty = new ArrayList<>();

        efekty.add(efekt);
    }

    public void setKolorNapoju(int kolorNapoju) {
        this.kolorNapoju = kolorNapoju;
    }

    public String getNazwa() {
        return nazwa;
    }
}
