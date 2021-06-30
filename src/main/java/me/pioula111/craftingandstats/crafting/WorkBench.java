package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.MenuHelper;
import me.pioula111.craftingandstats.markers.Marker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Iterator;

public class WorkBench {
    private String name;
    private String job;
    private HashSet<Recipe> recipes;

    public WorkBench(String name, Job job) {
        this.name = name;
        this.job = job.toString();
    }

    public void addRecipe(Recipe recipe) {
        if (recipes == null)
            recipes = new HashSet<>();

        recipes.add(recipe);
    }

    public String getName() {
        return name;
    }

    public boolean hasRecipe(String arg) {
        if (recipes == null)
            return false;

        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(arg))
                return true;
        }

        return false;
    }

    public void openMenu(Player player, Entity marker) {
        if (recipes == null) {
            player.sendMessage(ChatColor.RED + "Nie potrafisz nic z tym zrobić!");
            return;
        }

        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(MenuHelper.DECORATIONS))
                .append(Component.text().content(this.toString()).style(Style.style(MenuHelper.MAIN_NAME,TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ").style(Style.style(MenuHelper.DECORATIONS))).build();
        player.sendMessage(menu);
        Iterator<Recipe> it = recipes.iterator();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = it.next();
            HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                    Component.text().content("Naciśnij ")
                            .append(Component.text().content("LPM").style(Style.style(MenuHelper.LPM_COLOR,TextDecoration.BOLD)))
                            .append(Component.text().content(", aby stworzyć ten przedmiot!")).build());

            ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/wytworzprzedmiot " + marker.getLocation().getX() + " "
            + marker.getLocation().getY() + " " + marker.getLocation().getZ() + " " + name + " " + recipe.getName() + " " + CommandWytworzPrzedmiot.PASSWORD);

            player.sendMessage(Component.text().content("   " + (i + 1) + ". ").style(Style.style(MenuHelper.DECORATIONS)).append(Component.text()
                    .content(recipe.toString()).style(Style.style(MenuHelper.RECIPE_NAME)))
                    .clickEvent(clickEvent)
                    .hoverEvent(hov));
        }
    }

    @Override
    public String toString() {
        return name.replace("_"," ");
    }

    public Recipe getRecipe(String arg) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(arg))
                return recipe;
        }

        return null;
    }

    public static void removeWorkBenches(World world, String crafting) {
        crafting = crafting.replace("_"," ");
        for (Entity entity : world.getEntities()) {
            if (Marker.isMarker(entity)) {
                if (Marker.getName(entity).equals(crafting)) {
                    Marker.removeMarker(entity);
                }
            }
        }
    }

    public Component menuComponent(int nr) {
        HoverEvent<Component> hov = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
                Component.text().content("Naciśnij ")
                        .append(Component.text().content("LPM").style(Style.style(MenuHelper.LPM_COLOR, TextDecoration.BOLD)))
                        .append(Component.text().content(", aby zobaczyć receptury craftingu!")).build());

        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/receptury " + name);

        return Component.text().content("   " + nr + ". ").style(Style.style(MenuHelper.DECORATIONS)).append(Component.text()
                .content(this + "\n").style(Style.style(MenuHelper.RECIPE_NAME)))
                .clickEvent(clickEvent)
                .hoverEvent(hov).build();
    }

    public HashSet<Recipe> getRecipes() {
        return recipes;
    }

    public String getJob() {
        return job;
    }

    public void removeRecipe(String arg) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(arg)) {
                recipes.remove(recipe);
                break;
            }
        }
    }
}