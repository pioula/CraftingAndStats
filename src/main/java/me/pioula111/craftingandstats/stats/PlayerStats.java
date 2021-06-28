package me.pioula111.craftingandstats.stats;

public class PlayerStats {
    private int health, oneHanded, twoHanded, archery, strength, dexterity, mining, hunting;
    private int oneHandedHits, twoHandedHits, archeryHits, numOfDexAndStr, oreMined, mobsKilled;
    private int oneHandedLevelsADay, twoHandedLevelsADay, archeryLevelsADay, strengLevelsADay, dexterityLevelsADay;
    private int minigLevelsADay, huntingLevelsADay;
    private String job;
    public static final String archer = "Łuczarz";
    public static final String smith = "Kowal";
    public static final String alchemist = "Alchemik";
    public static final String noJob = "Nie Masz Fachu!";

    public PlayerStats() {
        resetStats();
    }

    public void resetStats() {
        health = 20;
        oneHanded = 10;
        twoHanded = 10;
        strength = 10;
        dexterity = 10;
        mining = 10;
        hunting = 10;
        archery = 10;
        oneHandedHits = 0;
        twoHandedHits = 0;
        archeryHits = 0;
        numOfDexAndStr = 0;
        oreMined = 0;
        mobsKilled = 0;
        oneHandedLevelsADay = 0;
        twoHandedLevelsADay = 0;
        archeryLevelsADay = 0;
        strengLevelsADay = 0;
        dexterityLevelsADay = 0;
        minigLevelsADay = 0;
        huntingLevelsADay = 0;
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

    public int getArchery() {
        return archery;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getHealth() {
        return health;
    }

    public int getHunting() {
        return hunting;
    }

    public int getMining() {
        return mining;
    }

    public int getOneHanded() {
        return oneHanded;
    }

    public int getStrength() {
        return strength;
    }

    public int getTwoHanded() {
        return twoHanded;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHunting(int hunting) {
        this.hunting = hunting;
    }

    public void setMining(int mining) {
        this.mining = mining;
    }

    public void setOneHanded(int oneHanded) {
        this.oneHanded = oneHanded;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setTwoHanded(int twoHanded) {
        this.twoHanded = twoHanded;
    }


}
