package me.pioula111.craftingandstats.items.properites;

import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.items.properites.weapons.LongDistance;
import me.pioula111.craftingandstats.items.properites.weapons.TwoHanded;
import me.pioula111.craftingandstats.items.properites.weapons.OneHanded;
import me.pioula111.craftingandstats.items.properites.tools.*;
import me.pioula111.craftingandstats.items.properites.statistics.Strength;
import me.pioula111.craftingandstats.items.properites.statistics.Dexterity;
import me.pioula111.craftingandstats.items.properites.additions.None;
import me.pioula111.craftingandstats.items.properites.additions.Upgrade;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class Property {
    public static Property deserialize(String s) {
        switch(s) {
            case "none":
                return new None();
            case "upgrade":
                return new Upgrade();
            case "strength":
                return new Strength();
            case "dexterity":
                return new Dexterity();
            case "pickaxe":
                return new Pickaxe();
            case "scythe":
                return new Scythe();
            case "sickle":
                return new Sickle();
            case "axe":
                return new Axe();
            case "fishing_rod":
                return new FishingRod();
            case "long_distance":
                return new LongDistance();
            case "one_handed":
                return new OneHanded();
            case "two_handed":
                return new TwoHanded();
        }

        return null;
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuHelper.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby dodać ulepszenie!")).build());

        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + this);

        return Component.text().content("   " + nr + ". ").style(Style.style(MenuHelper.DECORATIONS)).append(Component.text()
                .content(this.prettyToString() + "\n").style(Style.style(MenuHelper.RECIPE_NAME)))
                .clickEvent(clickEvent)
                .hoverEvent(hov).build();
    }

    @Override
    public abstract String toString();

    public abstract String prettyToString();
}
