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
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;

    public StatJsonOnQuit(StatManager statManager) {
        this.statManager = statManager;
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    public void writeToJson(File file, Player player) {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(gson.toJson(statManager.getPlayerStats(player)));
            statManager.removePlayer(player);
            writer.close();
        }
        catch(Exception ex) {
            System.out.println("[CraftingAndStats] Blad");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        File jsonFile = new File("plugins/CraftingAndStats/stats/" + event.getPlayer().getUniqueId() + ".json");

        writeToJson(jsonFile, event.getPlayer());
    }
}
