package me.pioula111.craftingandstats.stats.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.pioula111.craftingandstats.stats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class StatManager {
    private HashMap<String, PlayerStats> playersStats;
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;

    public StatManager() {
        this.playersStats = new HashMap<>();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    public HashMap<String, PlayerStats> getPlayersStats() {
        return playersStats;
    }

    public void setPlayersStats(HashMap<String, PlayerStats> playersStats) {
        this.playersStats = playersStats;
    }

    public boolean hasPlayer(Player player) {
        return playersStats != null && playersStats.containsKey(player.getName());
    }

    public PlayerStats getPlayerStats(Player player) {
        return playersStats.get(player.getName());
    }

    public void actualisePlayer(Player player, PlayerStats playerStats) {
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }

        playersStats.put(player.getName(), playerStats);
    }


    public void removePlayer(Player player) {
        playersStats.remove(player.getName());
    }

    private File makeFile(Player player) {
        return new File("plugins/CraftingAndStats/stats/" + player.getName() + "-" +  player.getUniqueId() + ".json");
    }

    public void savePlayer(Player player) {
        if (!hasPlayer(player))
            return;

        File file = makeFile(player);

        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(gson.toJson(getPlayerStats(player)));
            removePlayer(player);
            writer.close();
        }
        catch(Exception ex) {
            System.out.println("[CraftingAndStats] Blad");
        }
    }

    public void savePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            savePlayer(player);
        }
    }
}
