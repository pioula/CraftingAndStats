package me.pioula111.craftingandstats;

import org.bukkit.NamespacedKey;

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
        destroyerKey = new NamespacedKey(plugin, "destroyer");
        nazwaKey = new NamespacedKey(plugin, "nazwa");
        rodzajKey = new NamespacedKey(plugin, "rodzaj");
        podmienionyItemKey = new NamespacedKey(plugin, "podmienionyItem");
        typBroniKey = new NamespacedKey(plugin, "typBroni");
        dmgKey = new NamespacedKey(plugin, "dmg");
        ulepszenieKey = new NamespacedKey(plugin, "ulepszenie");
        wymaganaStatystykaKey = new NamespacedKey(plugin, "wymaganaStatystyka");
        wielkoscStatystykiKey = new NamespacedKey(plugin, "wielkoscStatystyki");
        obronaKey = new NamespacedKey(plugin, "obrona");
        typNarzedziaKey = new NamespacedKey(plugin, "typNarzedzia");
        efektyKey = new NamespacedKey(plugin, "efekty");
        kolorNapojuKey = new NamespacedKey(plugin, "kolorNapoju");
        autorKey = new NamespacedKey(plugin, "autor");
    }

}
