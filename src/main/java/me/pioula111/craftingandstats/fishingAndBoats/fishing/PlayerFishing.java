package me.pioula111.craftingandstats.fishingAndBoats.fishing;

import me.pioula111.craftingandstats.DropItemHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.items.myItems.MyOthers;
import me.pioula111.craftingandstats.markers.Marker;
import me.pioula111.craftingandstats.thievery.keyLocking.LootItem;
import me.pioula111.craftingandstats.thievery.keyLocking.json.LootManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class PlayerFishing implements Listener {
    private LootManager lootManager;
    private static final Random r = new Random();
    private static final int PROB_CATCHING_SALMON = 80;

    public PlayerFishing(LootManager lootManager) {
        this.lootManager = lootManager;
    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.BITE && event.getPlayer().isInWater())
            event.setCancelled(true);
        if (event.getCaught() != null && event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            event.getCaught().remove();
            ItemStack itemStack = createDrop(event.getPlayer());
            if (itemStack == null) {
                event.getPlayer().sendMessage(ChatColor.RED + "Ryba się zerwała!");
                return;
            }

            DropItemHelper.dropItem(event.getPlayer(), itemStack);
        }
    }

    private ItemStack createDrop(Player player) {
        if (hasBait(player)) {
            boolean isFisheryNearBy = false;
            for (Entity nearbyEntity : player.getNearbyEntities(10, 10, 10)) {
                if (Marker.isMarker(nearbyEntity) && Marker.getName(nearbyEntity) != null &&  Marker.getName(nearbyEntity).equals("Łowisko")) {
                    isFisheryNearBy = true;
                    break;
                }
            }
            if (isFisheryNearBy) {
                removeBait(player);
                if (isHappened(PROB_CATCHING_SALMON)) {
                    MyOthers drop = new MyOthers();
                    drop.setSwappedItem(Material.SLIME_BALL);
                    drop.setName("Surowy Łosoś");
                    return drop.makeItem(1);
                }
            }
        }

        for (LootItem item : lootManager.getItems()) {
            if (isHappened(item.getChance())) {
                return item.getItem().makeItem(1);
            }
        }

        return null;
    }

    private boolean isHappened(double prob) {
        return Math.abs(r.nextInt()) % 100 + 1 <= prob;
    }

    private void removeBait(Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (isBait(itemStack)) {
                itemStack.setAmount(itemStack.getAmount() - 1);
                if (itemStack.getAmount() <= 0) {
                    player.getInventory().removeItem(itemStack);
                }
            }
        }
    }

    private boolean hasBait(Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (isBait(itemStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean isBait(ItemStack itemStack) {
        return itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                itemStack.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals("Robak");
    }

}
