package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.harvestBlocks.BlockSheduler;
import me.pioula111.craftingandstats.harvestBlocks.HarvestBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class HSickle extends HTool implements Listener {
    public HSickle() {
        super();
        name = "sickle";
    }


    @Override
    public void onPlayerInteract(Player player, Block block) {
        ItemStack tool = player.getInventory().getItemInMainHand();
        HarvestBlock hBlock = getBlock(block);
        if (hBlock == null)
            return;
        CraftingAndStats plugin = JavaPlugin.getPlugin(CraftingAndStats.class);
        plugin.getBlockSheduler().sheduleBlockPlacement(block.getType(), block.getLocation(), respawnTime);
        block.setType(Material.AIR);
        player.getInventory().addItem(hBlock.getDrop().makeItem(1));
        Damageable meta = (Damageable) tool.getItemMeta();
        meta.setDamage(meta.getDamage() + 1);
        if (meta.getDamage() == tool.getType().getMaxDurability()) {
            player.getInventory().removeItem(tool);
        }
        else {
            tool.setItemMeta((ItemMeta) meta);
        }
    }

    @Override
    public String toString() {
        return "sickle";
    }

}
