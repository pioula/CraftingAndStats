package me.pioula111.craftingandstats.pvpAndPve.death;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.util.HashSet;

public class DeathManager implements Listener {
    private HashSet<String> players;

    public DeathManager() {
        players = new HashSet<>();
    }

    public void addPlayer(Player player) {
        players.add(player.getName());
        player.addPotionEffect(new PotionEffect(PotionEffectTypeWrapper.BLINDNESS, 600, 5));
        player.addPotionEffect(new PotionEffect(PotionEffectTypeWrapper.SLOW, 600, 1000));
    }


    public void removePlayer(Player player) {
        players.remove(player.getName());
        player.setHealth(10);
    }

    public boolean playerIsDead(Player player) {
        return players.contains(player.getName());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJump(PlayerMoveEvent event) {
        if (playerIsDead(event.getPlayer()) && event.getPlayer().getActivePotionEffects() != null && event.getPlayer().getActivePotionEffects().size() > 0)
            event.setCancelled(true);
    }
}
