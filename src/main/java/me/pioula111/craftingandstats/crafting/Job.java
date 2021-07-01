package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.gui.MenuHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.HashSet;

public class Job {
    private String name;
    private HashSet<Crafting> craftings;

    public Job(String name) {
        this.name = name;
        craftings = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addCrafting(Crafting newWorkbench) {
        if (craftings == null)
            craftings = new HashSet<>();
        craftings.add(newWorkbench);
    }

    public boolean hasCrafting(String arg) {
        if (craftings == null)
            return false;

        for (Crafting crafting : craftings) {
            if (crafting.getName().equals(arg))
                return true;
        }

        return false;
    }

    public void removeCrafting(String arg) {
        craftings.removeIf(workBench -> workBench.getName().equals(arg));
    }

    public Crafting getCrafting(String arg) {
        for (Crafting crafting : craftings) {
            if (crafting.getName().equals(arg))
                return crafting;
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name.replace("_", " ");
    }

    public HashSet<Crafting> getCraftings() {
        return craftings;
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuHelper.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby zobaczyć craftingi fachu!")).build());

        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/craftingi " + name);

        return Component.text().content("   " + nr + ". ").style(Style.style(MenuHelper.DECORATIONS)).append(Component.text()
                .content(this + "\n").style(Style.style(MenuHelper.RECIPE_NAME)))
                .clickEvent(clickEvent)
                .hoverEvent(hov).build();
    }
}
