package me.pioula111.craftingandstats.shoping.gui;

import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.ItemHelper;
import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.shoping.Good;
import me.pioula111.craftingandstats.shoping.Price;
import me.pioula111.craftingandstats.shoping.Shop;
import me.pioula111.craftingandstats.shoping.Stock;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ShopManagingGui {
    private static final String[] guiSetup = {
            "os     dm",
    };

    public static void showGui(Player player, Shop shop) {
        CraftingAndStats plugin = JavaPlugin.getPlugin(CraftingAndStats.class);
        InventoryGui gui = new InventoryGui(plugin , GuiHelper.createGuiTitle("Twój sklep"), guiSetup);

        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(new DynamicGuiElement('o',
                (viewer) -> new StaticGuiElement('o', GuiHelper.craftIcon("Zobacz swoją ofertę"),
                        click -> {
                            ShopGui shopGui = new ShopGui(player, shop);
                            gui.close();
                            shopGui.showGui(player);
                            return true;
                        })));
        gui.addElement(new DynamicGuiElement('s',
                (viewer) -> new StaticGuiElement('s', GuiHelper.craftIcon("Twoje towary"),
                        click -> {
                            plugin.getServer().getPluginManager().registerEvents(new StockGui(player, shop), plugin);
                            gui.close();
                            return true;
                        })));
        gui.addElement(new DynamicGuiElement('d',
                (viewer) -> new StaticGuiElement('d', GuiHelper.craftIcon("Usuń oferty"),
                        click -> {
                            DeletingShopGui deletingShopGui = new DeletingShopGui(player, shop);
                            gui.close();
                            deletingShopGui.showGui(player);
                            return true;
                        })));
        gui.addElement(new DynamicGuiElement('m',
                (viewer) -> new StaticGuiElement('m', createMoneyIcon(shop),
                        click -> {
                            ItemHelper.dropItem(player, shop.getEarntMoney().getGoldItem());
                            ItemHelper.dropItem(player, shop.getEarntMoney().getSilverItem());
                            ItemHelper.dropItem(player, shop.getEarntMoney().getBronzeItem());
                            shop.setEarntMoney(new Price( 0, 0, 0));
                            gui.close();
                            return true;
                        })));
        gui.show(player);
    }

    private static ItemStack createMoneyIcon(Shop shop) {
        ItemStack item = GuiHelper.craftIcon("Zarobione pieniądze");
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(ComponentWrapper.lore("Kliknij, aby wziąć zarobione pieniądze."));
        lore.add(ComponentWrapper.lore("Wartość pieniędzy w twoim sklepie:"));
        lore.add(ComponentWrapper.lore("Złote monety: " + shop.getEarntMoney().getGold()));
        lore.add(ComponentWrapper.lore("Srebrne monety: " + shop.getEarntMoney().getSilver()));
        lore.add(ComponentWrapper.lore("Miedziane monety: " + shop.getEarntMoney().getBronze()));
        item.lore(lore);
        return item;
    }
}
