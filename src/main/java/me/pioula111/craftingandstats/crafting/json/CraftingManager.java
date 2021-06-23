package me.pioula111.craftingandstats.crafting.json;

import me.pioula111.craftingandstats.crafting.Job;
import me.pioula111.craftingandstats.crafting.WorkBench;

import java.util.HashSet;

public class CraftingManager {
    private HashSet<Job> jobs;

    public CraftingManager() {
        jobs = new HashSet<>();
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public boolean hasJob(String arg) {
        for (Job job : jobs) {
            if (job.getName().equals(arg))
                return true;
        }

        return false;
    }

    public Job getJob(String arg) {
        for (Job job : jobs) {
            if (job.getName().equals(arg))
                return job;
        }

        return null;
    }

    public void removeJob(String arg) {
        jobs.removeIf(job -> job.getName().equals(arg));
    }

    public boolean hasCrafting(String arg) {
        for (Job job : jobs) {
            if (job.hasWorkBench(arg))
                return true;
        }

        return false;
    }

    public WorkBench getCrafting(String crafting) {
        for (Job job : jobs) {
            if (job.hasWorkBench(crafting)) {
                return job.getWorkBench(crafting);
            }
        }

        return null;
    }
}
