package me.pioula111.craftingandstats.stats.json;

import me.pioula111.craftingandstats.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class StatManager {
    private HashMap<String, PlayerStats> playersStats;

    public StatManager() {
        this.playersStats = new HashMap<>();
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
}
