package me.pioula111.craftingandstats.stats.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.FileWriter;

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
