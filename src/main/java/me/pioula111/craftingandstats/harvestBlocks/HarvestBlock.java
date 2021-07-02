package me.pioula111.craftingandstats.harvestBlocks;

import me.pioula111.craftingandstats.items.myItems.MyItem;
import org.bukkit.Material;

public class HarvestBlock {
    private Material block;
    private MyItem drop;

    public HarvestBlock(Material block, MyItem drop) {
        this.block = block;
        this.drop = drop;
    }

    public Material getBlock() {
        return block;
    }

    public MyItem getDrop() {
        return drop;
    }
}
