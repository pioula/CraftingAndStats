package me.pioula111.craftingandstats.itemy.rodzaje;

import me.pioula111.craftingandstats.MenuColors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class RodzajItemu {

    public static RodzajItemu serialize(String s) {
        switch(s) {
            case "bron":
                return new Bron();
            case "inne":
                return new Inne();
            case "napoj":
                return new Napoj();
            case "narzedzia":
                return new Narzedzia();
            case "pancerz":
                return new Pancerz();
            case "rzemieslniczy":
                return new Rzemieslniczy();
            case "zywnosc":
                return new Zywnosc();
        }
        return null;
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuColors.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby dodać rodzaj!")).build());

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
