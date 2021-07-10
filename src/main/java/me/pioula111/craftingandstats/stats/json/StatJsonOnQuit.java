package me.pioula111.craftingandstats.stats.json;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class StatJsonOnQuit implements Listener {
    private StatManager statManager;

    public StatJsonOnQuit(StatManager statManager) {
        this.statManager = statManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
       statManager.savePlayer(event.getPlayer());
    }
}
