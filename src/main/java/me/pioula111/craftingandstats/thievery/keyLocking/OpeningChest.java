package me.pioula111.craftingandstats.thievery.keyLocking;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.markers.Marker;
import me.pioula111.craftingandstats.stats.json.StatManager;
import me.pioula111.craftingandstats.thievery.keyLocking.gui.LockpickingGui;
import me.pioula111.craftingandstats.thievery.keyLocking.json.LootManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Random;

public class OpeningChest implements Listener {
    private StatManager statManager;
    private ChestManager chestManager;
    private LootManager lootManager;
    private static final Random r = new Random();

    public OpeningChest(StatManager statManager, ChestManager chestManager, LootManager lootManager) {
        this.statManager = statManager;
        this.chestManager = chestManager;
        this.lootManager = lootManager;
    }


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (Marker.isMarker(event.getEntity()) && (Marker.getName(event.getEntity()) != null) &&
                Marker.getName(event.getEntity()).equals("Ciężka Skrzynia")) {
            if (event.getDamager() instanceof Player) {
                event.setCancelled(true);
                if (chestManager.isOpened(event.getEntity().getLocation())) {
                    switch (Math.abs(r.nextInt() % 3)) {
                        case 0:
                            event.getDamager().sendMessage(ChatColor.RED + "Nic tu nie ma.");
                            break;
                        case 1:
                            event.getDamager().sendMessage(ChatColor.RED + "Nie ma czego plądrować.");
                            break;
                        case 2:
                            event.getDamager().sendMessage(ChatColor.RED + "Niczego tu nie znajdę.");
                            break;
                    }
                    return;
                }
                Player player = (Player) event.getDamager();
                boolean hasLockpick = false;
                for (ItemStack itemStack : player.getInventory()) {
                    if (itemStack != null && itemStack.getAmount() > 0 && itemStack.hasItemMeta() &&
                            itemStack.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                            itemStack.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals("Wytrych")) {
                        hasLockpick = true;
                        break;
                    }
                }

                if (!hasLockpick) {
                    player.sendMessage(ChatColor.RED + "Czym?");
                    return;
                }

                Location chestLoc = event.getEntity().getLocation();
                chestManager.setCode(chestLoc, generateCode());
                startLockpicking(player, chestManager.getCode(chestLoc), chestLoc);
            }
        }
    }

    private void startLockpicking(Player player, ArrayList<Boolean> code, Location chestLoc) {
        takeLockpick(player);
        LockpickingGui gui = new LockpickingGui(lootManager, statManager, chestManager);
        gui.createGui(player, code, chestLoc);
    }

    private void takeLockpick(Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && itemStack.getAmount() > 0 && itemStack.hasItemMeta() &&
                    itemStack.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                    itemStack.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals("Wytrych")) {
                player.getInventory().removeItem(itemStack.asOne());
                break;
            }
        }
    }

    private ArrayList<Boolean> generateCode() {
        //liczba zapadek

        long n = Math.abs(r.nextInt()) % 8 + 4;
        ArrayList<Boolean> code = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            code.add(r.nextBoolean());
        }

        return code;
    }
}
