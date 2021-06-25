package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.markers.Marker;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashSet;
import java.util.Set;

public class UsingAndRemovingWorkBench implements Listener {
    private CraftingManager craftingManager;
    private CraftingAndStats plugin;

    public UsingAndRemovingWorkBench(CraftingManager craftingManager, CraftingAndStats plugin) {
        this.craftingManager = craftingManager;
        this.plugin = plugin;
    }


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (Marker.isMarker(event.getEntity()) &&
                craftingManager.hasCrafting(Marker.getName(event.getEntity()))) {
            if (event.getDamager() instanceof Player) {
                event.setCancelled(true);
                craftingManager.getCrafting(Marker.getName(event.getEntity())).openMenu((Player) event.getDamager(), event.getEntity());
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (Marker.isMarker(event.getRightClicked()) &&
                craftingManager.hasCrafting(Marker.getName(event.getRightClicked()))) {
            if (event.getPlayer().getInventory().getItemInMainHand().getAmount() != 0 &&
                    event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(plugin.getDestroyerKey(), PersistentDataType.BYTE)
                    && event.getPlayer().isOp()) {
                Marker.removeMarker(event.getRightClicked());
            }
        }
    }
}
