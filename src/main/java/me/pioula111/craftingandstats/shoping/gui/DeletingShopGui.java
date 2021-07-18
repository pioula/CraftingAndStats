package me.pioula111.craftingandstats.shoping.gui;

import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.GuiElementGroup;
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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeletingShopGui {
    private GuiElementGroup[] firstRow;
    private GuiElementGroup[] secondRow;
    private InventoryGui gui;
    private Shop shop;
    private final String[] guiSetup = {
            " 0123456 ",
            "<abcdefg>",
            "         "
    };

    public DeletingShopGui(Player player, Shop shop) {
        firstRow = new GuiElementGroup[7];
        secondRow = new GuiElementGroup[7];
        this.shop = shop;
        for (int i = 0; i < firstRow.length; i++) {
            firstRow[i] = new GuiElementGroup((char)(i + '0'));
            secondRow[i] = new GuiElementGroup((char)(i + 'a'));
        }

        gui = new InventoryGui(JavaPlugin.getPlugin(CraftingAndStats.class), GuiHelper.createGuiTitle("Usuwanie Ofert"), guiSetup);

        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(GuiHelper.rightArrow('>'));
        gui.addElement(GuiHelper.leftArrow('<'));
    }

    public void showGui(Player player) {
        setGoods(shop.getGoods(), player, shop);

        for (int i = 0; i < firstRow.length; i++) {
            gui.addElement(firstRow[i]);
            gui.addElement(secondRow[i]);
        }

        gui.show(player);
    }

    private void setGoods(ArrayList<Good> goods, Player player, Shop shop) {
        Iterator<Good> it = goods.listIterator();
        for (int i = 0; i < goods.size(); i++) {
            int ind = i % 7;
            Good good = it.next();
            ItemStack result = good.getProduct().makeItem(good.getAmount());
            firstRow[ind].addElement(new StaticGuiElement((char)(ind + '0'), result));

            char tmp = (char)(ind + 'a');
            secondRow[ind].addElement(new DynamicGuiElement(tmp,
                    (viewer) -> new StaticGuiElement(tmp, createPrice(good.getPrice(), player, good),
                            click -> {
                                goods.remove(good);
                                shop.setGoods(goods);
                                player.sendMessage(ChatColor.GREEN + "Oferta została usunięta!");
                                gui.close();
                                return true;
                            })));
        }

        addEmptyColumns(7 - (goods.size() % 7));
    }

    private void addEmptyColumns(int emptyCols) {
        for (int i = 0; i < emptyCols; i++) {
            firstRow[6 - i].addElement(new StaticGuiElement((char)(6 - i + '0'), GuiHelper.getFiller()));
            secondRow[6 - i].addElement(new StaticGuiElement((char)(6 - i + 'a'), GuiHelper.getFiller()));
        }
    }

    private ItemStack createPrice(Price price, Player player, Good good) {
        ItemStack item = GuiHelper.craftIcon("Usuń tę ofertę");
        List<Component> lore = new ArrayList<>();

        ItemMeta itemMeta = item.getItemMeta();

        if (price.getGold() != 0)
            lore.add(ComponentWrapper.lore("Złota Moneta: " + price.getGold()));

        if (price.getSilver() != 0)
            lore.add(ComponentWrapper.lore("Srebrna Moneta: " + price.getSilver()));

        lore.add(ComponentWrapper.lore("Miedziana Moneta: " + price.getBronze()));

        item.setItemMeta(itemMeta);
        item.lore(lore);
        return item;

    }
}
