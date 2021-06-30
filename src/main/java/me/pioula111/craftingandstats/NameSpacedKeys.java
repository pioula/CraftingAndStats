package me.pioula111.craftingandstats;

import org.bukkit.NamespacedKey;

public class NameSpacedKeys {
    public static NamespacedKey KEY_DESTROYER;
    public static NamespacedKey KEY_NAME;
    public static NamespacedKey KEY_TYPE;
    public static NamespacedKey KEY_SWAPPED_ITEM;
    public static NamespacedKey KEY_WEAPON_TYPE;
    public static NamespacedKey KEY_DMG;
    public static NamespacedKey KEY_ADDITION;
    public static NamespacedKey KEY_STATISTIC;
    public static NamespacedKey KEY_STATISTIC_LEVEL;
    public static NamespacedKey KEY_ARMOR;
    public static NamespacedKey KEY_TOOL_TYPE;
    public static NamespacedKey KEY_EFFECTS;
    public static NamespacedKey KEY_DRINK_COLOR;
    public static NamespacedKey KEY_AUTHOR;
    private CraftingAndStats plugin;

    public NameSpacedKeys(CraftingAndStats plugin) {
        this.plugin = plugin;
        KEY_DESTROYER = new NamespacedKey(plugin, "destroyer");//BYTE
        KEY_NAME = new NamespacedKey(plugin, "name");//STRING
        KEY_TYPE = new NamespacedKey(plugin, "type");//STRING
        KEY_SWAPPED_ITEM = new NamespacedKey(plugin, "swapped_item");//STRING
        KEY_WEAPON_TYPE = new NamespacedKey(plugin, "weapon_type");//STRING
        KEY_DMG = new NamespacedKey(plugin, "dmg");//Double
        KEY_ADDITION = new NamespacedKey(plugin, "addition");//STRING
        KEY_STATISTIC = new NamespacedKey(plugin, "statistic");//STRING
        KEY_STATISTIC_LEVEL = new NamespacedKey(plugin, "statistic_level");//INT
        KEY_ARMOR = new NamespacedKey(plugin, "armor");//INT
        KEY_TOOL_TYPE = new NamespacedKey(plugin, "tool_type");//STRING
        KEY_EFFECTS = new NamespacedKey(plugin, "effects");//STRING
        KEY_DRINK_COLOR = new NamespacedKey(plugin, "drink_color");//INT
        KEY_AUTHOR = new NamespacedKey(plugin, "author");//STRING
    }
}
