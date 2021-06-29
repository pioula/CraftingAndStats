package me.pioula111.craftingandstats.itemy.napoje;

import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.util.ArrayList;

public class Efekt {
    private PotionEffectType typEfektu;
    private int czasTrwania;
    private int moc;

    public Efekt(PotionEffectType typEfektu, int czasTrwania, int moc) {
        this.typEfektu = typEfektu;
        this.czasTrwania = czasTrwania;
        this.moc = moc;
    }

    public static ArrayList<Efekt> serialize(String s) {
        ArrayList<Efekt> efekty = new ArrayList<>();
        s = s.replace("[","");
        s = s.replace("]","");
        s = s.replace(" ","");
        String[] arr = s.split(",");
        for (String s1 : arr) {
            String[] efekt = s1.split("#");
            efekty.add(new Efekt(PotionEffectTypeWrapper.getByName(efekt[0]), Integer.parseInt(efekt[1]), Integer.parseInt(efekt[2])));
        }

        return efekty;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public int getMoc() {
        return moc;
    }

    public PotionEffectType getTypEfektu() {
        return typEfektu;
    }

    @Override
    public String toString() {
        return typEfektu.getName() + "#" + czasTrwania + "#" + moc;
    }
}
