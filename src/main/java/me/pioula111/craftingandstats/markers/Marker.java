package me.pioula111.craftingandstats.markers;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class Marker implements Listener {
    public static void newMarker(int size, Player player, String name) {
        Location spawnPoint = player.getLocation();
        spawnPoint.setY(spawnPoint.getY() - 1);
        spawnPoint.setX(Math.floor(spawnPoint.getX()) + 0.5);
        spawnPoint.setZ(Math.floor(spawnPoint.getZ()) + 0.5);

        Slime marker = (Slime) spawnPoint.getWorld().spawnEntity(spawnPoint, EntityType.SLIME);
        marker.setSize(size);
        marker.setInvisible(true);
        marker.setPersistent(true);
        marker.setInvulnerable(true);
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
    }

    public static boolean isMarker(Entity entity) {
        return entity.getType() == EntityType.SLIME && entity.isSilent();
    }

    public static void removeMarker(Entity marker) {
        List<Entity> passengers = marker.getPassengers();
        for (Entity passenger : passengers) {
            passenger.remove();
        }

        marker.remove();
    }

    public static String getName(Entity marker) {
        List<Entity> passengers = marker.getPassengers();
        if (passengers.size() == 0)
            return null;
        StringBuilder builder = new StringBuilder();
        String name = passengers.get(0).getName();

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ')
                builder.append("_");
            else
                builder.append(name.charAt(i));
        }

        return builder.toString();
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (Marker.isMarker(event.getEntity()))
            event.setCancelled(true);
    }
}
