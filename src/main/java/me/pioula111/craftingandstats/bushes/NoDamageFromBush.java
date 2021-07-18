package me.pioula111.craftingandstats.bushes;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class NoDamageFromBush implements Listener {

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        if (event.getDamager() != null && event.getDamager().getType() == Material.SWEET_BERRY_BUSH) {
            event.setCancelled(true);
        }
    }
}
