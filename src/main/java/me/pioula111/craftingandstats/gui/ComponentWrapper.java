package me.pioula111.craftingandstats.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class ComponentWrapper {

    public static Component itemName(String name) {
        return Component.text().content(name).decoration(TextDecoration.ITALIC, false).build();
    }
    public static Component lore(String lore) {
        return Component.text().content(lore).style(MenuHelper.LORE_COLOR).build();
    }
}
