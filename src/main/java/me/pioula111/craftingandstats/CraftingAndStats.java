package me.pioula111.craftingandstats;

import me.pioula111.craftingandstats.crafting.*;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.markers.Marker;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class CraftingAndStats extends JavaPlugin {
    private NamespacedKey destroyerKey = new NamespacedKey(this, "destroyer");
    private CraftingJsonManager jsonManager;
    private File jsonFile;


    @Override
    public void onEnable() {
        // Plugin startup logic
        jsonFile = new File("plugins/CraftingAndStats/crafting_and_stats.json");
        jsonManager = new CraftingJsonManager(jsonFile);

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new UsingAndRemovingWorkBench(jsonManager.getCraftingManager(), this), this);
        pluginManager.registerEvents(new Marker(), this);

        Objects.requireNonNull(this.getCommand("nowareceptura")).setExecutor(new CommandNowaReceptura(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("nowycrafting")).setExecutor(new CommandNowyCrafting(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("nowyfach")).setExecutor(new CommandNowyFach(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("postawcrafting")).setExecutor(new CommandPostawCrafting(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("destroyer")).setExecutor(new CommandDestroyer(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        jsonManager.writeToJson();
    }

    public NamespacedKey getDestroyerKey() {
        return destroyerKey;
    }
}
