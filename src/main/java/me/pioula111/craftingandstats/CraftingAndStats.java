package me.pioula111.craftingandstats;

import me.pioula111.craftingandstats.crafting.*;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.itemy.*;
import me.pioula111.craftingandstats.itemy.komendy.*;
import me.pioula111.craftingandstats.markers.Marker;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class CraftingAndStats extends JavaPlugin {
    private CraftingJsonManager jsonManager;
    private File jsonFile;
    private NameSpacedKeys nameSpacedKeys;

    @Override
    public void onEnable() {
        // Plugin startup logic
        jsonFile = new File("plugins/CraftingAndStats/crafting_and_stats.json");
        jsonManager = new CraftingJsonManager(jsonFile);
        nameSpacedKeys = new NameSpacedKeys(this);


        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new UsingAndRemovingWorkBench(jsonManager.getCraftingManager(), this), this);
        pluginManager.registerEvents(new Marker(), this);

        Objects.requireNonNull(this.getCommand("nowareceptura")).setExecutor(new CommandNowaReceptura(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("nowycrafting")).setExecutor(new CommandNowyCrafting(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("nowyfach")).setExecutor(new CommandNowyFach(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("postawcrafting")).setExecutor(new CommandPostawCrafting(jsonManager.getCraftingManager()));
        Objects.requireNonNull(this.getCommand("destroyer")).setExecutor(new CommandDestroyer(this));
        Objects.requireNonNull(this.getCommand("wytworzprzedmiot")).setExecutor(new CommandWytworzPrzedmiot(jsonManager.getCraftingManager()));

        ItemManager itemManager = new ItemManager();
        Objects.requireNonNull(this.getCommand("stworzitem")).setExecutor(new CommandStworzItem(itemManager));

        Objects.requireNonNull(this.getCommand("rzemieslniczy")).setExecutor(new CommandRzemieslniczy(itemManager));
        Objects.requireNonNull(this.getCommand("zywnosc")).setExecutor(new CommandZywnosc(itemManager));
        Objects.requireNonNull(this.getCommand("inne")).setExecutor(new CommandInne(itemManager));
        Objects.requireNonNull(this.getCommand("bron")).setExecutor(new CommandBron(itemManager));

        Objects.requireNonNull(this.getCommand("jednoreczna")).setExecutor(new CommandJednoreczna(itemManager));
        Objects.requireNonNull(this.getCommand("dwureczna")).setExecutor(new CommandDwureczna(itemManager));
        Objects.requireNonNull(this.getCommand("dlugodystansowa")).setExecutor(new CommandDlugodystansowa(itemManager));

        Objects.requireNonNull(this.getCommand("dmg")).setExecutor(new CommandDmg(itemManager));
        Objects.requireNonNull(this.getCommand("zrecznosc")).setExecutor(new CommandZrecznosc(itemManager));
        Objects.requireNonNull(this.getCommand("sila")).setExecutor(new CommandSila(itemManager));

        Objects.requireNonNull(this.getCommand("statystyka")).setExecutor(new CommandStatystyka(itemManager));
        Objects.requireNonNull(this.getCommand("brak")).setExecutor(new CommandBrak(itemManager));
        Objects.requireNonNull(this.getCommand("wzmocnienie")).setExecutor(new CommandWzmocnienie(itemManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        jsonManager.writeToJson();
    }
}
