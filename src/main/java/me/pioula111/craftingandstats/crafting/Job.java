package me.pioula111.craftingandstats.crafting;

import java.util.ArrayList;
import java.util.HashSet;

public class Job {
    private String name;
    private HashSet<WorkBench> workBenches;

    public Job(String name) {
        this.name = name;
        workBenches = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addWorkbench(WorkBench newWorkbench) {
        if (workBenches == null)
            workBenches = new HashSet<>();
        workBenches.add(newWorkbench);
    }

    public boolean hasWorkBench(String arg) {
        if (workBenches == null)
            return false;

        for (WorkBench workBench : workBenches) {
            if (workBench.getName().equals(arg))
                return true;
        }

        return false;
    }

    public void removeWorkBench(String arg) {
        workBenches.removeIf(workBench -> workBench.getName().equals(arg));
    }

    public WorkBench getWorkBench(String arg) {
        for (WorkBench workBench : workBenches) {
            if (workBench.getName().equals(arg))
                return workBench;
        }

        return null;
    }
}
