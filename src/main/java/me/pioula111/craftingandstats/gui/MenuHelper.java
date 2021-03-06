package me.pioula111.craftingandstats.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class MenuHelper {
    public final static TextColor DECORATIONS = TextColor.color(0x2C3394);
    public final static TextColor MAIN_NAME = TextColor.color(0x8088FF);
    public final static TextColor RECIPE_NAME = TextColor.color(0x947B1E);
    public final static TextColor LPM_COLOR = TextColor.color(0xDECA1B);
    public static final Style LORE_COLOR = Style.style(TextColor.color(0x009999));

    public static TextComponent createMenu(String mainName) {
        return Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(MenuHelper.DECORATIONS))
                .append(Component.text().content(mainName).style(Style.style(MenuHelper.MAIN_NAME, TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ\n").style(Style.style(MenuHelper.DECORATIONS))).build();
    }
}
