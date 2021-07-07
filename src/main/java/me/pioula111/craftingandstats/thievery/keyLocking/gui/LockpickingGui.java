package me.pioula111.craftingandstats.thievery.keyLocking.gui;

import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.GuiElementGroup;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.crafting.Ingredient;
import me.pioula111.craftingandstats.crafting.Recipe;
import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.items.myItems.MyOthers;
import me.pioula111.craftingandstats.stats.json.StatManager;
import me.pioula111.craftingandstats.thievery.keyLocking.ChestManager;
import me.pioula111.craftingandstats.thievery.keyLocking.json.LootManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;

public class LockpickingGui {
    private InventoryGui gui;
    private LootManager lootManager;
    private StatManager statManager;
    private ChestManager chestManager;
    private final String[] guiSetup = {
            "   <c>   "
    };


    public LockpickingGui(LootManager lootManager, StatManager statManager, ChestManager chestManager) {
        this.lootManager = lootManager;
        this.chestManager = chestManager;
        this.statManager = statManager;
    }

    public void createGui(Player player, ArrayList<Boolean> code, Location chestLoc) {
        gui = new InventoryGui(JavaPlugin.getPlugin(CraftingAndStats.class), GuiHelper.createGuiTitle("Otwieranie zamka"), guiSetup);

        Iterator<Boolean> it = code.iterator();
        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(new StaticGuiElement('c', GuiHelper.craftIcon("Ciężka Skrzynia")));
        gui.addElement(new DynamicGuiElement('<',
                (viewer) -> new StaticGuiElement('<', GuiHelper.craftIcon("Obróć w lewo"),
                        click -> {
                            if (!it.hasNext()) {
                                gui.close();
                                giveLockpick(player);
                                openChest(player, chestLoc);
                                return true;
                            }
                            else if (it.next()) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 100, 0);
                            }
                            else {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 100, 0);
                                player.sendMessage(ChatColor.RED + "Wytrych się złamał!");
                                gui.close();
                            }

                            click.getGui().draw();
                            return true;
                        })));
        gui.addElement(new DynamicGuiElement('>',
                (viewer) -> new StaticGuiElement('>', GuiHelper.craftIcon("Obróć w prawo"),
                        click -> {
                            if (!it.hasNext()) {
                                gui.close();
                                openChest(player, chestLoc);
                                return true;
                            }
                            else if (!it.next()) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 100, 2);
                            }
                            else {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 100, 0);
                                player.sendMessage(ChatColor.RED + "Wytrych się złamał!");
                                gui.close();

                            }

                            click.getGui().draw();
                            return true;
                        })));
        gui.show(player);
    }

    private void giveLockpick(Player player) {
        MyOthers lockpick = new MyOthers();
        lockpick.setName("Wytrych");
        lockpick.setSwappedItem(Material.SLIME_BALL);
        player.getInventory().addItem(lockpick.makeItem(1));
    }

    private void openChest(Player player, Location chestLoc) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 100, 0);
        statManager.getPlayerStats(player).increaseStatExp(player, "dexterity", 10);
        lootManager.showContains(player);
        chestManager.openChest(chestLoc);
    }
}

//player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 100, 0);
//player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 100, 2);
