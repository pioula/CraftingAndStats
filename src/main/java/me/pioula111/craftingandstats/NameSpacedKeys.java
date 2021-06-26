package me.pioula111.craftingandstats;

import org.bukkit.NamespacedKey;

public class NameSpacedKeys {
    public static NamespacedKey destroyerKey;
    public static NamespacedKey nazwaKey;
    public static NamespacedKey rodzajKey;
    public static NamespacedKey podmienionyItemKey;
    private CraftingAndStats plugin;

    public NameSpacedKeys(CraftingAndStats plugin) {
        this.plugin = plugin;
        destroyerKey = new NamespacedKey(plugin, "destroyer");
        nazwaKey = new NamespacedKey(plugin, "nazwa");
        rodzajKey = new NamespacedKey(plugin, "rodzaj");
        podmienionyItemKey = new NamespacedKey(plugin, "podmienionyItem");
    }

}
