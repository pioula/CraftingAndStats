package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import de.themoep.inventorygui.*;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.harvestBlocks.HarvestBlock;
import me.pioula111.craftingandstats.harvestBlocks.IncreasedStat;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.stats.json.StatManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class HTool implements Listener {
    protected HashSet<HarvestBlock> blocks;
    protected String name;
    protected long respawnTime = 45; //45 minutes
    protected ArrayList<IncreasedStat> stats;
    protected Material blockReplacement;
    private static final int NUMBER_OF_SLOTS = 21;
    private static final String[] guiSetup = {
            " abcdefg ",
            " hijklmn ",
            " opqrstu "
    };

    public HTool() {
        this.blocks = new HashSet<>();
        stats = new ArrayList<>();
    }

    public boolean hasSpace() {
        return blocks.size() < NUMBER_OF_SLOTS;
    }

    public void addBlock(HarvestBlock block) {
        blocks.removeIf(harvestBlock -> harvestBlock.getBlock().equals(block.getBlock()));
        blocks.add(block);
    }

    public void showBlocks(Player player) {
        InventoryGui gui = new InventoryGui(JavaPlugin.getPlugin(CraftingAndStats.class), GuiHelper.createGuiTitle(name), guiSetup);

        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(GuiHelper.rightArrow('>'));
        gui.addElement(GuiHelper.leftArrow('<'));

        Iterator<HarvestBlock> it = blocks.iterator();
        for (int i = 0; i < blocks.size(); i++) {
            HarvestBlock block = it.next();
            char tmp = (char)(i + 'a');
            DynamicGuiElement harvestBlock = new DynamicGuiElement(tmp,
                    (viewer) -> new StaticGuiElement(tmp, createBlock(block.getBlock(), block.getDrop()),
                            click -> {
                                blocks.remove(block);
                                click.getGui().draw();
                                return true;
                            }));
            gui.addElement(harvestBlock);
        }

        gui.show(player);
    }

    private ItemStack createBlock(Material block, MyItem drop) {
        ItemStack item = new ItemStack(block);
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(ComponentWrapper.lore(drop.getName().replace("_"," ")));
        item.lore(lore);
        return item;
    }

    @Nullable
    protected HarvestBlock getBlock(@NotNull Block block) {
        for (HarvestBlock harvestBlock : blocks) {
            if (harvestBlock.getBlock().equals(block.getType()))
                return harvestBlock;
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void onPlayerInteract(Player player, Block block) {
        CraftingAndStats plugin = JavaPlugin.getPlugin(CraftingAndStats.class);
        StatManager statManager = plugin.getStatManager();
        ItemStack tool = player.getInventory().getItemInMainHand();

        HarvestBlock hBlock = getBlock(block);
        if (hBlock == null)
            return;

        plugin.getBlockSheduler().sheduleBlockPlacement(block.getType(), block.getLocation(), respawnTime);
        block.setType(blockReplacement);
        player.getInventory().addItem(getLoot(hBlock, player));
        Damageable meta = (Damageable) tool.getItemMeta();
        meta.setDamage(meta.getDamage() + 1);

        for (IncreasedStat stat : stats) {
            statManager.getPlayerStats(player).increaseStatExp(player, stat.getStatName(), stat.getExp());
        }

        if (meta.getDamage() == tool.getType().getMaxDurability()) {
            player.getInventory().removeItem(tool);
        }
        else {
            tool.setItemMeta((ItemMeta) meta);
        }
    }

    public ItemStack getLoot(HarvestBlock hBlock, Player player) {
        return hBlock.getDrop().makeItem(1);
    }
}
