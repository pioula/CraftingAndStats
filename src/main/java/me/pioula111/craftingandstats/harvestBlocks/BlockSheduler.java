package me.pioula111.craftingandstats.harvestBlocks;

import me.pioula111.craftingandstats.CraftingAndStats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.PriorityQueue;
import java.util.Random;

public class BlockSheduler {
    private final Random r = new Random();
    private PriorityQueue<BrokenBlock> queue;
    private static final long taskPeriod = 300L; //15 sekund


    public BlockSheduler(CraftingAndStats plugin) {
        queue = new PriorityQueue<>();
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                while (!queue.isEmpty()) {
                    if (queue.peek().milisLeft() <= 0) {
                        BrokenBlock block = queue.poll();
                        block.getLocation().getWorld().getBlockAt(block.getLocation()).setType(block.getBlock());
                    }
                }
            }
        }, 20L, taskPeriod);
    }



    public void sheduleBlockPlacement(Material block, Location location, long respawnTime) {
        queue.add(new BrokenBlock(block, location, minutesToMilis(respawnTime)));
    }

    public long minutesToMilis(long minutes) {
        return minutes * 60000; //60 sekund * 1000 milisekund
    }

    public void placeAllBlocks() {
        while (!queue.isEmpty()) {
            BrokenBlock block = queue.poll();
            block.getLocation().getWorld().getBlockAt(block.getLocation()).setType(block.getBlock());
        }
    }
}
