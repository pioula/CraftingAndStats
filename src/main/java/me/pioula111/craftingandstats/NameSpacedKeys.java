package me.pioula111.craftingandstats;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

public class NameSpacedKeys {
    public static NamespacedKey destroyerKey;
    public static NamespacedKey nazwaKey;
    public static NamespacedKey rodzajKey;
    public static NamespacedKey podmienionyItemKey;
    public static NamespacedKey typBroniKey;
    public static NamespacedKey dmgKey;
    public static NamespacedKey ulepszenieKey;
    public static NamespacedKey wymaganaStatystykaKey;
    public static NamespacedKey wielkoscStatystykiKey;
    public static NamespacedKey obronaKey;
    public static NamespacedKey typNarzedziaKey;
    public static NamespacedKey efektyKey;
    public static NamespacedKey kolorNapojuKey;
    public static NamespacedKey autorKey;
    private CraftingAndStats plugin;

    public NameSpacedKeys(CraftingAndStats plugin) {
        this.plugin = plugin;
        destroyerKey = new NamespacedKey(plugin, "destroyer");//BYTE
        nazwaKey = new NamespacedKey(plugin, "nazwa");//STRING
        rodzajKey = new NamespacedKey(plugin, "rodzaj");//STRING
        podmienionyItemKey = new NamespacedKey(plugin, "podmienionyItem");//STRING
        typBroniKey = new NamespacedKey(plugin, "typBroni");//STRING
        dmgKey = new NamespacedKey(plugin, "dmg");//INT
        ulepszenieKey = new NamespacedKey(plugin, "ulepszenie");//STRING
        wymaganaStatystykaKey = new NamespacedKey(plugin, "wymaganaStatystyka");//STRING
        wielkoscStatystykiKey = new NamespacedKey(plugin, "wielkoscStatystyki");//INT
        obronaKey = new NamespacedKey(plugin, "obrona");//INT
        typNarzedziaKey = new NamespacedKey(plugin, "typNarzedzia");//STRING
        efektyKey = new NamespacedKey(plugin, "efekty");//STRING
        kolorNapojuKey = new NamespacedKey(plugin, "kolorNapoju");//INT
        autorKey = new NamespacedKey(plugin, "autor");//STRING
    }
}
