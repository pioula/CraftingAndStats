package me.pioula111.craftingandstats.fishingAndBoats.boats;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;


public class PreventBoatDestroying implements Listener {
    @EventHandler
    public void onBoatDestroy(VehicleDestroyEvent event) {
        if (event.getVehicle().getType() == EntityType.BOAT) {
            event.setCancelled(true);
        }
    }
}
