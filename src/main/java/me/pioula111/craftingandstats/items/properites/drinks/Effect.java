package me.pioula111.craftingandstats.items.properites.drinks;

import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.util.ArrayList;

public class Effect {
    private PotionEffectType effectType;
    private int time;
    private int level;

    public Effect(PotionEffectType effectType, int time, int level) {
        this.effectType = effectType;
        this.time = time;
        this.level = level;
    }

    public static ArrayList<Effect> deserialize(String s) {
        ArrayList<Effect> efekty = new ArrayList<>();
        s = s.replace("[","");
        s = s.replace("]","");
        s = s.replace(" ","");
        String[] arr = s.split(",");
        for (String s1 : arr) {
            String[] efekt = s1.split("#");
            efekty.add(new Effect(PotionEffectTypeWrapper.getByName(efekt[0]), Integer.parseInt(efekt[1]), Integer.parseInt(efekt[2])));
        }

        return efekty;
    }

    public int getTime() {
        return time;
    }

    public int getLevel() {
        return level;
    }

    public PotionEffectType getEffectType() {
        return effectType;
    }

    @Override
    public String toString() {
        return effectType.getName() + "#" + time + "#" + level;
    }
}
