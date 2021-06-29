package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.harvestBlocks.HarvestBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public abstract class HarvestTool implements Listener {
    protected HashSet<HarvestBlock> blocks;

    public HarvestTool() {
        this.blocks = new HashSet<>();
    }

    public void addBlock(HarvestBlock block) {
        blocks.add(block);
    }

    @EventHandler
    public abstract void onPlayerInteract(PlayerInteractEvent event);
}
