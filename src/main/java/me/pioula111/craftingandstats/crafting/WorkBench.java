package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.markers.Marker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashSet;

public class WorkBench {
    private String name;
    private Job job;
    private HashSet<Recipe> recipes;
    private final static TextColor decorations = TextColor.color(0x2C3394);
    private final static TextColor mainName = TextColor.color(0x8088FF);
    private final static TextColor recipeName = TextColor.color(0x947B1E);

    public WorkBench(String name, Job job) {
        this.name = name;
        this.job = job;
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

    public void openMenu(Player player) {
        if (recipes == null) {
            player.sendMessage(ChatColor.RED + "Nie potrafisz nic z tym zrobić!");
            return;
        }

        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(decorations))
                .append(Component.text().content(this.toString()).style(Style.style(mainName,TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ").style(Style.style(decorations))).build();
        player.sendMessage(menu);
        for (int i = 0; i < recipes.size(); i++) {
            player.sendMessage(Component.text().content("   " + (i + 1) + ". ").style(Style.style(decorations))
                    .append(Component.text().content(recipes.iterator().next().toString()).style(Style.style(recipeName))).build());

        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '_') {
                builder.append(" ");
            }
            else {
                builder.append(name.charAt(i));
            }
        }

        return builder.toString();
    }
}