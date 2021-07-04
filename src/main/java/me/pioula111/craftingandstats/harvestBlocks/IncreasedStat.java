package me.pioula111.craftingandstats.harvestBlocks;

public class IncreasedStat {
    private String statName;
    private int exp;

    public IncreasedStat(String statName, int exp) {
        this.statName = statName;
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public String getStatName() {
        return statName;
    }
}
