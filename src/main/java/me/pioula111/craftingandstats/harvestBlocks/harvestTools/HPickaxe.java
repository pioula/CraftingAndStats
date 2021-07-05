package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.RandomHelper;
import me.pioula111.craftingandstats.harvestBlocks.HarvestBlock;
import me.pioula111.craftingandstats.harvestBlocks.IncreasedStat;
import me.pioula111.craftingandstats.items.myItems.MyHandCraft;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class HPickaxe extends HTool implements Listener {

    public HPickaxe() {
        super();
        name = "pickaxe";
        stats.add(new IncreasedStat("strength", 1));
        stats.add(new IncreasedStat("mining", 10));
        this.blockReplacement = Material.STONE;
    }

    @Override
    public ItemStack getLoot(HarvestBlock hBlock, Player player) {
        StatManager statManager = JavaPlugin.getPlugin(CraftingAndStats.class).getStatManager();
        if (RandomHelper.hasHappened(statManager.getPlayerStats(player).getStat("mining"))) {
            return hBlock.getDrop().makeItem(1);
        }
        else {
            MyItem item = new MyHandCraft();
            item.setName("Krzemień");
            item.setSwappedItem(Material.SLIME_BALL);
            return item.makeItem(1);
        }
    }
}
