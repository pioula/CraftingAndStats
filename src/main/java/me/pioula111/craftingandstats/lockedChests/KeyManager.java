package me.pioula111.craftingandstats.lockedChests;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.lockedChests.json.LockedManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;


public class KeyManager implements Listener {
    private LockedManager lockedManager;
    private static final String keyName = "Klucz";

    public KeyManager(LockedManager lockedManager) {
        this.lockedManager = lockedManager;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND && event.getClickedBlock() != null &&
                lockedManager.isOpenable(event.getClickedBlock()) && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();

            if (isDoor(clickedBlock.getType())) {
                Location doorsLocation = clickedBlock.getLocation();
                doorsLocation.setY(doorsLocation.getY() - 1);
                if (isDoor(event.getPlayer().getWorld().getBlockAt(doorsLocation).getType())) {
                    clickedBlock = event.getPlayer().getWorld().getBlockAt(doorsLocation);
                }
            }

            Player player = event.getPlayer();
            String blockKey = String.valueOf(clickedBlock.getBlockKey());

            if (isKey(player.getInventory().getItemInMainHand())) {
                if ((player.isOp() || lockedManager.isOwner(player, blockKey)) && player.isSneaking()) {
                    lockedManager.createKey(player.getInventory().getItemInMainHand(), blockKey);
                    player.sendMessage(ChatColor.GREEN + "Dodano klucz!");
                    event.setCancelled(true);
                }
                else {
                    if (!lockedManager.canOpen(player.getInventory().getItemInMainHand(), blockKey)) {
                        player.sendMessage(ChatColor.RED + "Twój klucz nie pasuje!");
                        event.setCancelled(true);
                    }
                }
            }
            else {
                if (!player.isOp()) {
                    player.sendMessage(ChatColor.RED + "Potrzebujesz odpowiedniego klucza, by móc to otworzyć!");
                    event.setCancelled(true);
                }
            }
        }

    }

    public static boolean isKey(ItemStack key) {
        return key != null && key.getAmount() > 0 && key.hasItemMeta() && key.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                key.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME,PersistentDataType.STRING).equals(keyName);
    }

    private boolean isDoor(Material block) {
        switch (block) {
            case DARK_OAK_DOOR:
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case CRIMSON_DOOR:
            case JUNGLE_DOOR:
            case OAK_DOOR:
            case SPRUCE_DOOR:
            case WARPED_DOOR:
                return true;
        }

        return false;
    }
}
