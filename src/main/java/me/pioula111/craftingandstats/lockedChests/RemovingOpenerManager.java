package me.pioula111.craftingandstats.lockedChests;

import me.pioula111.craftingandstats.lockedChests.json.LockedManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class RemovingOpenerManager implements Listener {
    private LockedManager lockedManager;

    public RemovingOpenerManager(LockedManager lockedManager) {
        this.lockedManager = lockedManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        String blockLoc = String.valueOf(event.getBlock().getBlockKey());
        if (lockedManager.hasBlock(blockLoc)) {
            lockedManager.removeBlock(blockLoc);
        }
    }
}
