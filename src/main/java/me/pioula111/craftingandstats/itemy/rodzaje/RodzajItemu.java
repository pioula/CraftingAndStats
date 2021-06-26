package me.pioula111.craftingandstats.itemy.rodzaje;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class RodzajItemu {
    private final static TextColor ozdobyK = TextColor.color(0x2C3394);
    private final static TextColor nazwaK = TextColor.color(0x8088FF);
    private final static TextColor rodzajK = TextColor.color(0x947B1E);
    private final static TextColor LPMK = TextColor.color(0xDECA1B);

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(LPMK, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby dodać rodzaj!")).build());

        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + this.toString());

        return Component.text().content("   " + nr + ". ").style(Style.style(ozdobyK)).append(Component.text()
                .content(this.prettyToString() + "\n").style(Style.style(rodzajK)))
                .clickEvent(clickEvent)
                .hoverEvent(hov).build();
    }

    @Override
    public abstract String toString();

    public abstract String prettyToString();
}
