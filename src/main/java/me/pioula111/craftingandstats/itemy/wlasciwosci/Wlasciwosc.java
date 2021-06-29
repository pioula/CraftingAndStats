package me.pioula111.craftingandstats.itemy.wlasciwosci;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.Dlugodystansowa;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.Dwureczna;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.Jednoreczna;
import me.pioula111.craftingandstats.itemy.wlasciwosci.narzedzia.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.rodzaje.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.statystyki.Sila;
import me.pioula111.craftingandstats.itemy.wlasciwosci.statystyki.Zrecznosc;
import me.pioula111.craftingandstats.itemy.wlasciwosci.ulepszenia.Brak;
import me.pioula111.craftingandstats.itemy.wlasciwosci.ulepszenia.Wzmocnienie;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class Wlasciwosc {
    public static Wlasciwosc deserialize(String s) {
        switch(s) {
            case "brak":
                return new Brak();
            case "wzmocnienie":
                return new Wzmocnienie();
            case "sila":
                return new Sila();
            case "zrecznosc":
                return new Zrecznosc();
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
            case "dlugodystansowa":
                return new Dlugodystansowa();
            case "jednoreczna":
                return new Jednoreczna();
            case "dwureczna":
                return new Dwureczna();
        }

        return null;
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuColors.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby dodać ulepszenie!")).build());

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
