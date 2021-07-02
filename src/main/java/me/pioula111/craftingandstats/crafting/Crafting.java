package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.gui.CraftingMenu;
import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.markers.Marker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class Crafting {
    private final String name;
    private final String job;
    private final List<Recipe> recipes = new ArrayList<>();


    public Crafting(String name, Job job) {
        this.name = name;
        this.job = job.toString();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public String getName() {
        return name;
    }

    public boolean hasRecipe(String arg) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(arg))
                return true;
        }

        return false;
    }

    public void openMenu(Player player) {
        CraftingMenu craftingMenu = new CraftingMenu(this.getName().replace("_"," "));

        craftingMenu.setRecipes(recipes, player);
        craftingMenu.showToPlayer(player);
    }

    @Override
    public String toString() {
        return name.replace("_"," ");
    }

    public static void removeWorkBenches(World world, String crafting) {
        crafting = crafting.replace("_"," ");
        for (Entity entity : world.getEntities()) {
            if (Marker.isMarker(entity) && Marker.getName(entity).equals(crafting)) {
                Marker.removeMarker(entity);
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

    public List<Recipe> getRecipes() {
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