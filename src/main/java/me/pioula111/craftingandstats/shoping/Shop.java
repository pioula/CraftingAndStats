package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.ItemHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.shoping.gui.ShopGui;
import me.pioula111.craftingandstats.shoping.gui.ShopManagingGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class Shop {
    private static final String PERMITION = "Zezwolenie na sklep";
    private String owner;
    private ArrayList<Good> goods;
    private ArrayList<Stock> stock;
    private Price earntMoney;
    private long ownerRentTime;
    private static final long WEEK = 604800000; //7 dni * 24 godzin * 60 minut * 60 sekund * 1000 milisekund

    public Shop() {
        this.goods = new ArrayList<>();
        this.stock = new ArrayList<>();
        earntMoney = new Price(0, 0, 0);
    }

    public Price getEarntMoney() {
        return earntMoney;
    }

    public void setEarntMoney(Price earntMoney) {
        this.earntMoney = earntMoney;
    }

    public void openMenu(Player customer) {
        if (owner != null && System.currentTimeMillis() - ownerRentTime > WEEK) {
            owner = null;
            earntMoney = new Price(0, 0, 0);
            goods = new ArrayList<>();
            stock = new ArrayList<>();
        }
        if (owner != null && customer.isOp()) {
            customer.sendMessage(ChatColor.RED + "Sklep gracza " + owner);
        }
        if (owner != null && (customer.isOp() || owner.equals(customer.getName()))) {
            customer.sendMessage(ChatColor.RED + "Pozostało " + getTimeLeft() + "do usunięcia sklepu.");
        }

        if (owner == null && isPermition(customer.getInventory().getItemInMainHand())) {
            ItemHelper.takeItems(customer.getInventory(), PERMITION, 1);
            owner = customer.getName();
            customer.sendMessage(ChatColor.GREEN + "Teraz ten sklep należy do ciebie przez następne 7 dni!");
            ownerRentTime = System.currentTimeMillis();
            return;
        }

        if (owner == null) {
            customer.sendMessage(ChatColor.RED + "Ten sklep nie posiada właściciela!");
            return;
        }

        if (owner.equals(customer.getName())) {
            manageShop(customer);
            //zarządzanie sklepem
        }
        else {
            showGoods(customer);
            //towary
        }
    }

    private void showGoods(Player customer) {
        ShopGui shopGui = new ShopGui(customer, this);
        shopGui.showGui(customer);
    }

    private void manageShop(Player shopOwner) {
        ShopManagingGui.showGui(shopOwner, this);
    }

    private String getTimeLeft() {
        long time = ownerRentTime + WEEK - System.currentTimeMillis();
        StringBuilder timeLeft = new StringBuilder();
        long tmp = WEEK / 7;
        timeLeft.append(time / tmp).append(" dni ");
        time -= (time / tmp) * tmp; //część całkowita

        tmp /= 24;
        timeLeft.append(time / tmp).append(" godzin ");
        time -= (time / tmp) * tmp; //część całkowita

        tmp /= 60;
        timeLeft.append(time / tmp).append(" minut ");
        time -= (time / tmp) * tmp; //część całkowita

        tmp /= 60;
        timeLeft.append(time / tmp).append(" sekund ");

        return timeLeft.toString();
    }

    private boolean isPermition(ItemStack item) {
        return item != null && item.getAmount() > 0 && item.hasItemMeta() &&
                item.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                item.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(PERMITION);
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public ArrayList<Stock> getStock() {
        return stock;
    }

    public String getOwner() {
        return owner;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public void setStock(ArrayList<Stock> stock) {
        this.stock = stock;
    }
}
