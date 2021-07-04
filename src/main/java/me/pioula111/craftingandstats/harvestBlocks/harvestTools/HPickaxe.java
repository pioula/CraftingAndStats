package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.harvestBlocks.HarvestBlock;
import me.pioula111.craftingandstats.harvestBlocks.IncreasedStat;
import me.pioula111.craftingandstats.items.myItems.MyHandCraft;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;

public class HPickaxe extends HTool implements Listener {
    private static final Random r = new Random();

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
        if (hasHappened(statManager.getPlayerStats(player).getStat("mining"))) {
            return hBlock.getDrop().makeItem(1);
        }
        else {
            MyItem item = new MyHandCraft();
            item.setName("Krzemień");
            item.setSwappedItem(Material.SLIME_BALL);
            return item.makeItem(1);
        }
    }

    private boolean hasHappened(Long mining) {
        int result = Math.abs(r.nextInt()) % 100 + 1;
        return  result <= mining;
    }
}
