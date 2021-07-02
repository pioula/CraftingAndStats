package me.pioula111.craftingandstats.harvestBlocks.json;

import me.pioula111.craftingandstats.harvestBlocks.harvestTools.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class HarvestManager {
    private static final int NUMBER_OF_TOOLS = 4;
    private ArrayList<HTool> tools;

    public HarvestManager() {
        this.tools = new ArrayList<>(NUMBER_OF_TOOLS);
        tools.add(new HPickaxe());
        tools.add(new HScythe());
        tools.add(new HSickle());
        tools.add(new HAxe());
    }

    @Nullable
    public HTool getTool(@NotNull String name) {
        for (HTool tool : tools) {
            if (tool.getName().equals(name)) {
                return tool;
            }
        }

        return null;
    }
}
