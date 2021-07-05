package me.pioula111.craftingandstats.stats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerStats {
    private HashMap<String, Long> stats;
    private String job;
    public static final String archer = "Łuczarz";
    public static final String smith = "Kowal";
    public static final String alchemist = "Alchemik";
    public static final String noJob = "Nie Masz Fachu!";
    private static final long statExp = 1000;
    private static final long maxStatLevelUpsADay = 3;
    private static final long maxStatLevel = 100;
    private static final long day = 86400000; // 24 godziny

    public PlayerStats() {
        resetStats();
    }

    public void resetStats() {
        stats = new HashMap<>();
        stats.put("health", 20L);
        stats.put("one_handed", 10L);
        stats.put("two_handed", 10L);
        stats.put("strength", 10L);
        stats.put("dexterity", 10L);
        stats.put("mining", 10L);
        stats.put("hunting", 10L);
        stats.put("long_distance", 10L);
        setJobNone();
    }

    public void setJobArcher() {
        job = archer;
    }

    public void setJobSmith() {
        job = smith;
    }

    public void setJobAlchemist() {
        job = alchemist;
    }

    public void setJobNone() {
        job = noJob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getStat(String stat) {
        statCheck(stat);

        return stats.get(stat);
    }

    public void increaseStatExp(Player player, String stat, int value) {
        if (getStat(stat) == maxStatLevel)
            return;

        String lastLevelUps = stat + "Last";
        if (getStat(lastLevelUps) + day <= System.currentTimeMillis()) {
            String statExp = stat + "Exp";

            stats.put(statExp, getStat(statExp) + value);
            if (getStat(statExp) >= PlayerStats.statExp) {
                String statThisDay = stat + "ThisDay";

                stats.put(statThisDay, getStat(statThisDay) + 1);
                stats.put(stat, Math.min(getStat(stat) + 1, maxStatLevel));
                stats.put(statExp, 0L);
                if (getStat(statThisDay) == maxStatLevelUpsADay) {
                    stats.put(lastLevelUps, System.currentTimeMillis());
                    stats.put(statThisDay, 0L);
                }
                player.sendMessage(ChatColor.GREEN + "Twoja statystyka wzrosła!");
            }
        }
    }

    private void statCheck(String stat) {
        if (!stats.containsKey(stat))
            stats.put(stat, 0L);
    }
}
