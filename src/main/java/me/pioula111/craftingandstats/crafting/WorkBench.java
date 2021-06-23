package me.pioula111.craftingandstats.crafting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class WorkBench {
    private String name;
    private Job job;
    private ArrayList<Recipe> recipes;

    public WorkBench(String name, Job job) {
        this.name = name;
        this.job = job;
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
        player.sendMessage(ChatColor.GREEN + "Menu!");
    }
}
