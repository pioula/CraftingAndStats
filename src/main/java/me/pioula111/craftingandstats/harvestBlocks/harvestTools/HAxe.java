package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.EventListener;

public class HAxe extends HTool implements EventListener {
    public HAxe() {
        super();
        name = "axe";
    }

    @Override
    public void onPlayerInteract(Player player, Block block) {

    }
}
