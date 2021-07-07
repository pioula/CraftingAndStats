package me.pioula111.craftingandstats.thievery.keyLocking.json;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.CraftingMenu;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.thievery.keyLocking.LootItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Random;

public class LootManager implements Listener {
    private ArrayList<LootItem> items;
    private Inventory inv;
    private final static Random r = new Random();

    public LootManager() {
        this.items = new ArrayList<>();
    }

    //Służy do konfiguracji
    public void showLoots(Player sender) {
        if (inv != null) {
            sender.sendMessage(ChatColor.RED + "ktoś aktualnie zmienia loot!");
            return;
        }
        inv = Bukkit.createInventory(null, InventoryType.CHEST, Component.text().content(GuiHelper.createGuiTitle("Loot")).build());
        for (int i = 0; i < items.size(); i++) {
            inv.setItem(i, items.get(i).getItem().makeItem(items.get(i).getMaxAmount()));
        }
        sender.openInventory(inv);
    }

    public void addNewItem(ItemStack itemStack, Player player) {
        if (itemStack != null && itemStack.getAmount() > 0) {
            MyItem item = MyItem.fromItemStack(itemStack);
            if (item == null && player != null) {
                player.sendMessage(ChatColor.RED + "Nie dodano itemu " + itemStack + " ponieważ nie jest zrobiony przy pomocy /stworzitem!");
            }
            else {
                items.add(new LootItem(item, 100, itemStack.getAmount()));
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (inv != null && event.getInventory().equals(inv)) {
            saveInventory((Player) event.getPlayer());
        }
    }

    private boolean areSimilar(ItemStack itemStack1, ItemStack itemStack2) {
        return itemStack1.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(
                itemStack2.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING)) && itemStack1.getAmount() == itemStack2.getAmount();

    }

    public void saveInventory(Player player) {
        if (inv == null)
            return;
        int i = 0;
        boolean isTheSame = true;
        for (ItemStack itemStack : inv) {
            if (i >= items.size()) {
                break;
            }
            else if (itemStack != null && !areSimilar(items.get(i).getItem().makeItem(items.get(i).getMaxAmount()), itemStack)) {
                isTheSame = false;
                break;
            }
            i++;
        }

        if (!isTheSame) {
            if (player != null)
                player.sendMessage(ChatColor.RED + "Należy ustawić na nowo wszystkie szanse dropu!");
            items = new ArrayList<>();
            for (ItemStack itemStack : inv) {
                addNewItem(itemStack, player);
            }
        }
        else {
            i = 0;
            for (ItemStack itemStack : inv) {
                if (i >= items.size()) {
                    addNewItem(itemStack, player);
                }
                i++;
            }
        }
        inv = null;
    }

    //służy do pokazania zawartości skrzyni
    public void showContains(Player player) {
        Inventory chestContains = Bukkit.createInventory(null, InventoryType.CHEST, Component.text().content(GuiHelper.createGuiTitle("Ciężka Skrzynia")).build());

        for (LootItem item : items) {
            if (hasHappened(item.getChance())) {
                int place;
                 do {
                    place = Math.abs(r.nextInt() % 27);
                } while (chestContains.getItem(place) != null);
                chestContains.setItem(place, item.getItem().makeItem(Math.abs(r.nextInt()) % item.getMaxAmount() + 1));
            }
        }

        player.openInventory(chestContains);
    }

    private boolean hasHappened(double prob) {
        return Math.abs(r.nextInt()) % 100 + 1 <= prob;
    }

    public Component showChances() {
        Component menu = MenuHelper.createMenu("Loot");
        for (int i = 0; i < items.size(); i++) {
            menu = menu.append(Component.text().content("[" + (i + 1) + "] ").color(MenuHelper.DECORATIONS).build()).append(Component.text().content(items.get(i) + "\n").color(MenuHelper.RECIPE_NAME).build());
        }

        return menu;
    }

    public ArrayList<LootItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LootItem> items) {
        this.items = items;
    }
}
