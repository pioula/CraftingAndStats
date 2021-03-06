package me.pioula111.craftingandstats.crafting.json;

import me.pioula111.craftingandstats.crafting.Job;
import me.pioula111.craftingandstats.crafting.Crafting;

import java.util.HashSet;

public class CraftingManager {
    private HashSet<Job> jobs;

    public CraftingManager() {
        jobs = new HashSet<>();
    }

    public void addJob(Job job) {
        if (jobs == null)
            jobs = new HashSet<>();

        jobs.add(job);
    }

    public boolean hasJob(String arg) {
        if (jobs == null)
            return false;

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
        if (jobs == null || arg == null)
            return false;

        for (Job job : jobs) {
            if (job.hasCrafting(arg))
                return true;
        }

        return false;
    }

    public Crafting getCrafting(String crafting) {
        for (Job job : jobs) {
            if (job.hasCrafting(crafting)) {
                return job.getCrafting(crafting);
            }
        }

        return null;
    }

    public HashSet<Job> getJobs() {
        return jobs;
    }
}
