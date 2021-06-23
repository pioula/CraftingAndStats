package me.pioula111.craftingandstats.helpers;

public class Pair <T1, T2> {
    private T1 key;
    private T2 value;

    public Pair(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T2 getValue() {
        return value;
    }

    public T1 getKey() {
        return key;
    }
}
