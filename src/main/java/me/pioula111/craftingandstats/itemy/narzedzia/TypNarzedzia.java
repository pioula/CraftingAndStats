package me.pioula111.craftingandstats.itemy.narzedzia;

import me.pioula111.craftingandstats.MenuColors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class TypNarzedzia {

    public static TypNarzedzia serialize(String s) {
        switch(s) {
            case "kilof":
                return new Kilof();
            case "kosa":
                return new Kosa();
            case "sierp":
                return new Sierp();
            case "topor":
                return new Topor();
            case "wedka":
                return new Wedka();
        }

        return null;
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuColors.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby dodać typ narzędzia!")).build());

        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + this);

        return Component.text().content("   " + nr + ". ").style(Style.style(MenuColors.DECORATIONS)).append(Component.text()
                .content(this.prettyToString() + "\n").style(Style.style(MenuColors.RECIPE_NAME)))
                .clickEvent(clickEvent)
                .hoverEvent(hov).build();
    }

    @Override
    public abstract String toString();

    public abstract String prettyToString();
}
