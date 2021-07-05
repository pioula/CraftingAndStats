package me.pioula111.craftingandstats;

import java.util.Random;

public class RandomHelper {
    private static final Random r = new Random();


    public static boolean hasHappened(Long prob) {
        int result = Math.abs(r.nextInt()) % 100 + 1;
        return result <= prob;
    }
}
