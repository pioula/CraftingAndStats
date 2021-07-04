package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.harvestBlocks.IncreasedStat;
import org.bukkit.Material;
import org.bukkit.event.Listener;

public class HSickle extends HTool implements Listener {
    public HSickle() {
        super();
        name = "sickle";
        stats.add(new IncreasedStat("dexterity", 1));
        this.blockReplacement = Material.AIR;
    }

    @Override
    public String toString() {
        return "sickle";
    }

}
