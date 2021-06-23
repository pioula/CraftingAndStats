package me.pioula111.craftingandstats.crafting;

import java.util.ArrayList;

public class WorkBench {
    private String name;
    private Job job;
    private ArrayList<Recipe> recipes;

    public WorkBench(String name, Job job) {
        this.name = name;
        this.job = job;
    }

}
