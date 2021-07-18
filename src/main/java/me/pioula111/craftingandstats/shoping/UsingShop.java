package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.crafting.Crafting;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.markers.Marker;
import me.pioula111.craftingandstats.shoping.json.ShopManager;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

public class UsingShop implements Listener {
    private ShopManager shopManager;
    private CraftingAndStats plugin;


    public UsingShop (ShopManager shopManager, CraftingAndStats plugin) {
        this.shopManager = shopManager;
        this.plugin = plugin;
    }


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (Marker.isMarker(event.getEntity()) &&
                shopManager.hasShop(event.getEntity())) {
            if (event.getDamager() instanceof Player) {
                event.setCancelled(true);
                Shop shop = shopManager.getShop(event.getEntity());

                shop.openMenu((Player) event.getDamager());
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND && Marker.isMarker(event.getRightClicked())) {
            if (event.getPlayer().getInventory().getItemInMainHand().getAmount() != 0 &&
                    event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_DESTROYER, PersistentDataType.BYTE)
                    && event.getPlayer().isOp()) {
                shopManager.removeShop(event.getRightClicked());
            }
        }
    }
}
