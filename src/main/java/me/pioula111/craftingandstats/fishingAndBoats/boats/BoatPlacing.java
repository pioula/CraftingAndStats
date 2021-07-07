package me.pioula111.craftingandstats.fishingAndBoats.boats;

import me.pioula111.craftingandstats.NameSpacedKeys;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BoatPlacing implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if ((event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) && isBoat(event.getPlayer().getInventory().getItemInMainHand())) {
            if (!event.getPlayer().isInWater()) {
                event.getPlayer().sendMessage(ChatColor.RED + "Aby postawić łódkę należy być w wodzie!");
                return;
            }
            Player player = event.getPlayer();
            removeBoatItem(player);
            placeBoat(player, player.getLocation());
        }
    }

    private void placeBoat(Player owner, Location location) {
        location.setY(location.getY() + 1);
        Boat boat = (Boat) location.getWorld().spawnEntity(location, EntityType.BOAT);
        boat.setWoodType(TreeSpecies.DARK_OAK);
        boat.getPersistentDataContainer().set(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING, owner.getUniqueId().toString());
        boat.addPassenger(owner);
    }

    private void removeBoatItem(Player player) {
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        if (player.getInventory().getItemInMainHand().getAmount() <= 0)
            player.getInventory().removeItem(player.getInventory().getItemInMainHand());
    }

    private boolean isBoat(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
        item.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals("Drakkar");
    }
}
