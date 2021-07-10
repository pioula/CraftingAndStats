package me.pioula111.craftingandstats.pvpAndPve.death;

import me.pioula111.craftingandstats.CraftingAndStats;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    private DeathManager deathManager;
    private CraftingAndStats plugin;


    public PlayerDeath(DeathManager deathManager, CraftingAndStats plugin) {
        this.deathManager = deathManager;
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setCancelled(true);
        deathManager.addPlayer(event.getEntity());
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                deathManager.removePlayer(event.getEntity());
            }
        }, 666L);
    }
}
