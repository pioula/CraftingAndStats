package me.pioula111.craftingandstats.markers;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class Marker implements Listener {
    public static Entity newMarker(int size, Player player, String name) {
        Location spawnPoint = player.getLocation();
        spawnPoint.setY(spawnPoint.getY() - 1);
        spawnPoint.setX(Math.floor(spawnPoint.getX()) + 0.5);
        spawnPoint.setZ(Math.floor(spawnPoint.getZ()) + 0.5);

        Slime marker = (Slime) spawnPoint.getWorld().spawnEntity(spawnPoint, EntityType.SLIME);
        marker.setSize(size);
        marker.setInvisible(true);
        marker.setPersistent(true);
        marker.setRemoveWhenFarAway(false);
        marker.setCollidable(false);
        marker.setAware(false);
        marker.setWander(false);
        marker.setAI(false);
        marker.setCanPickupItems(false);
        marker.setSilent(true);

        if (name != null) {
            ArmorStand nametag = (ArmorStand) spawnPoint.getWorld().spawnEntity(spawnPoint, EntityType.ARMOR_STAND);
            nametag.setMarker(true);
            nametag.setInvisible(true);
            nametag.setCustomName(name);
            nametag.setCustomNameVisible(true);
            marker.addPassenger(nametag);
        }

        return marker;
    }

    public static boolean isMarker(Entity entity) {
        return entity.getType() == EntityType.SLIME && entity.isSilent();
    }

    public static void removeMarker(Entity marker) {
        List<Entity> passengers = marker.getPassengers();
        for (Entity passenger : passengers) {
            passenger.remove();
        }

        for (Entity nearbyEntity : marker.getNearbyEntities(0.1, 0.75, 0.1)) {
            if (nearbyEntity.getType() == EntityType.ARMOR_STAND) {
                nearbyEntity.remove();
            }
        }

        marker.remove();
    }

    public static String getName(Entity marker) {
        List<Entity> passengers = marker.getPassengers();
        if (passengers.size() == 0) {
            for (Entity nearbyEntity : marker.getNearbyEntities(0.1, 0.75, 0.1)) {
                if (nearbyEntity.getType() == EntityType.ARMOR_STAND) {
                    return nearbyEntity.getName();
                }
            }
            return "";
        }

        return passengers.get(0).getName();
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (Marker.isMarker(event.getEntity()))
            event.setCancelled(true);
    }
}
