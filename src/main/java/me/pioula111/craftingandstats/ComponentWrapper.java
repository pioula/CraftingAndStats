package me.pioula111.craftingandstats;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class ComponentWrapper {

    public static Component itemName(String name) {
        return Component.text().content(name.replace("_"," ")).decoration(TextDecoration.ITALIC, false).build();
    }
}
