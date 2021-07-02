package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class HScythe extends HTool implements Listener {
    public HScythe() {
        super();
        name = "scythe";
    }

    @Override
    public void onPlayerInteract(Player player, Block block) {

    }
}
