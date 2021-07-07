package me.pioula111.craftingandstats.fishingAndBoats.boats;

import me.pioula111.craftingandstats.DropItemHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.items.myItems.MyOthers;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BoatRemoving implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND && event.getRightClicked().getType() == EntityType.BOAT &&
        event.getRightClicked().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) && event.getPlayer().isSneaking()) {
            if (event.getRightClicked().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(event.getPlayer().getUniqueId().toString())) {
                event.getRightClicked().remove();
                DropItemHelper.dropItem(event.getPlayer(), createBoatItem());
            }
            else {
                event.getPlayer().sendMessage(ChatColor.RED + "Nie jesteś właścicielem tej łodzi!");
            }
        }
        else if(event.getRightClicked().getType() == EntityType.BOAT && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() &&
                event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_DESTROYER, PersistentDataType.BYTE) && event.getPlayer().isOp()) {
            event.getRightClicked().remove();
        }
    }

    private ItemStack createBoatItem() {
        MyOthers boat = new MyOthers();
        boat.setName("Drakkar");
        boat.setSwappedItem(Material.SLIME_BALL);
        return boat.makeItem(1);
    }
}
