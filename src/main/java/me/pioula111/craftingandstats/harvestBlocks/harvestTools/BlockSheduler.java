package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.CraftingAndStats;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class BlockSheduler {
    private static final Random r = new Random();

    public static void sheduleBlockPlacement(Material block, Location location) {
        CraftingAndStats plugin = JavaPlugin.getPlugin(CraftingAndStats.class);
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                location.getWorld().getBlockAt(location).setType(block);
            }
        }, getTime());
    }

    private static long getTime() {
        return r.nextLong() % 100 + 20;
    }
}
