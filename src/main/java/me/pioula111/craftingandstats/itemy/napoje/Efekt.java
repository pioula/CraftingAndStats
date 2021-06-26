package me.pioula111.craftingandstats.itemy.napoje;

import org.bukkit.potion.PotionEffectType;

public class Efekt {
    private PotionEffectType typEfektu;
    private int czasTrwania;
    private int moc;

    public Efekt(PotionEffectType typEfektu, int czasTrwania, int moc) {
        this.typEfektu = typEfektu;
        this.czasTrwania = czasTrwania;
        this.moc = moc;
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
        return typEfektu.getName() + "," + czasTrwania + "," + moc;
    }
}
