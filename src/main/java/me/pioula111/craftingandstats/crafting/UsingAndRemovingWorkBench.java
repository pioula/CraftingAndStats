package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.markers.Marker;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class UsingAndRemovingWorkBench implements Listener {
    private CraftingManager craftingManager;
    private CraftingAndStats plugin;
    private StatManager statManager;


    public UsingAndRemovingWorkBench(CraftingManager craftingManager, CraftingAndStats plugin, StatManager statManager) {
        this.craftingManager = craftingManager;
        this.plugin = plugin;
        this.statManager = statManager;
    }


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (Marker.isMarker(event.getEntity()) &&
                craftingManager.hasCrafting(Marker.getName(event.getEntity()))) {
            if (event.getDamager() instanceof Player) {
                event.setCancelled(true);
                WorkBench workBench = craftingManager.getCrafting(Marker.getName(event.getEntity()));
                boolean isAllowed = true;

                Player player = (Player) event.getDamager();

                switch(workBench.getJob()) {
                    case "Kowal":
                        isAllowed = statManager.getPlayerStats(player).getJob().equals("Kowal");
                        break;
                    case "Alchemik":
                        isAllowed = statManager.getPlayerStats(player).getJob().equals("Alchemik");
                        break;
                    case "Łuczarz":
                        isAllowed = statManager.getPlayerStats(player).getJob().equals("Łuczarz");
                        break;
                }

                if(!isAllowed) {
                    player.sendMessage(ChatColor.RED + "Nie potrafisz nic z tym zrobić!");
                    return;
                }

                workBench.openMenu((Player) event.getDamager(), event.getEntity());
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (Marker.isMarker(event.getRightClicked()) &&
                craftingManager.hasCrafting(Marker.getName(event.getRightClicked()))) {
            if (event.getPlayer().getInventory().getItemInMainHand().getAmount() != 0 &&
                    event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_DESTROYER, PersistentDataType.BYTE)
                    && event.getPlayer().isOp()) {
                Marker.removeMarker(event.getRightClicked());
            }
        }
    }
}
