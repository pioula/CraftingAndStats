package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.harvestBlocks.IncreasedStat;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.EventListener;

public class HAxe extends HTool implements EventListener {
    public HAxe() {
        super();
        name = "axe";
        stats.add(new IncreasedStat("strength", 1));
        this.blockReplacement = Material.AIR;
    }
}
