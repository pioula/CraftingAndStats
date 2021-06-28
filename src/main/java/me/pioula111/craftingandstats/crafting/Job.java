package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.MenuColors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.HashSet;

public class Job {
    private String name;
    private HashSet<WorkBench> workBenches;

    public Job(String name) {
        this.name = name;
        workBenches = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addWorkbench(WorkBench newWorkbench) {
        if (workBenches == null)
            workBenches = new HashSet<>();
        workBenches.add(newWorkbench);
    }

    public boolean hasWorkBench(String arg) {
        if (workBenches == null)
            return false;

        for (WorkBench workBench : workBenches) {
            if (workBench.getName().equals(arg))
                return true;
        }

        return false;
    }

    public void removeWorkBench(String arg) {
        workBenches.removeIf(workBench -> workBench.getName().equals(arg));
    }

    public WorkBench getWorkBench(String arg) {
        for (WorkBench workBench : workBenches) {
            if (workBench.getName().equals(arg))
                return workBench;
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name.replace("_", " ");
    }

    public HashSet<WorkBench> getWorkBenches() {
        return workBenches;
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuColors.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby zobaczyć craftingi fachu!")).build());

        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/craftingi " + name);

        return Component.text().content("   " + nr + ". ").style(Style.style(MenuColors.DECORATIONS)).append(Component.text()
                .content(this + "\n").style(Style.style(MenuColors.RECIPE_NAME)))
                .clickEvent(clickEvent)
                .hoverEvent(hov).build();
    }
}
