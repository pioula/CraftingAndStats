package me.pioula111.craftingandstats.shoping.gui;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.shoping.Shop;
import me.pioula111.craftingandstats.shoping.Stock;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class StockGui implements Listener {
    private Shop shop;
    private Inventory inv;
    private boolean wasClosed = false;

    public StockGui(Player player, Shop shop) {
        this.shop = shop;
        inv = Bukkit.createInventory(null, InventoryType.CHEST, Component.text().content(GuiHelper.createGuiTitle("Twoje towary")).build());
        for (int i = 0; i < shop.getStock().size(); i++) {
            inv.setItem(i, shop.getStock().get(i).getItem().makeItem(shop.getStock().get(i).getAmount()));
        }

        shop.setStock(new ArrayList<>());
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!wasClosed && shop.getOwner() != null && event.getPlayer().getName().equals(shop.getOwner())) {
            wasClosed = true;
            ArrayList<Stock> stock = new ArrayList<>();
            for (ItemStack content : inv.getContents()) {
                if (content != null && content.getAmount() > 0)
                    stock.add(new Stock(MyItem.fromItemStack(content), content.getAmount()));
            }
            shop.setStock(stock);
        }
    }
}
