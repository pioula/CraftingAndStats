package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.harvestBlocks.IncreasedStat;
import org.bukkit.Material;
import org.bukkit.event.Listener;

public class HScythe extends HTool implements Listener {
    public HScythe() {
        super();
        stats.add(new IncreasedStat("dexterity", 1));
        name = "scythe";
        this.blockReplacement = Material.AIR;
    }
}
