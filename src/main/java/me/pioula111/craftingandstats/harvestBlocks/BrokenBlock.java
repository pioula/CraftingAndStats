package me.pioula111.craftingandstats.harvestBlocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class BrokenBlock implements Comparable<BrokenBlock> {
    private Material block;
    private Location location;
    private long breakTime;
    private long respawnTime;

    public BrokenBlock(Material block, Location location, long respawnTime) {
        this.block = block;
        this.location = location;
        this.respawnTime = respawnTime;
        breakTime = System.currentTimeMillis();
    }

    public long milisLeft() {
        return respawnTime - System.currentTimeMillis() + breakTime;
    }

    public long appearTime() {
        return breakTime + respawnTime;
    }

    @Override
    public int compareTo(@NotNull BrokenBlock o) {
        return appearTime() - o.appearTime() > 0 ? 1 : -1;
    }

    public Location getLocation() {
        return location;
    }

    public Material getBlock() {
        return block;
    }
}
