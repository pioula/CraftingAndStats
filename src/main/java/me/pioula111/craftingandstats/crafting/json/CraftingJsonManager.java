package me.pioula111.craftingandstats.crafting.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.pioula111.craftingandstats.crafting.json.deserializers.*;
import me.pioula111.craftingandstats.crafting.json.serializers.*;
import me.pioula111.craftingandstats.itemy.bronie.TypBroni;
import me.pioula111.craftingandstats.itemy.napoje.Efekt;
import me.pioula111.craftingandstats.itemy.narzedzia.TypNarzedzia;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;
import me.pioula111.craftingandstats.itemy.statystyki.Statystyka;
import me.pioula111.craftingandstats.itemy.ulepszenia.Ulepszenie;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class CraftingJsonManager {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;
    private File file;
    private JsonReader reader;
    private CraftingManager craftingManager;

    public CraftingJsonManager(File file) {
        gsonBuilder.setPrettyPrinting();
        craftingManager = new CraftingManager();

        gsonBuilder.registerTypeAdapter(Efekt.class, new EfektSerializer());
        gsonBuilder.registerTypeAdapter(Material.class, new MaterialSerializer());
        gsonBuilder.registerTypeAdapter(RodzajItemu.class, new RodzajItemuSerializer());
        gsonBuilder.registerTypeAdapter(Statystyka.class, new StatystykaSerializer());
        gsonBuilder.registerTypeAdapter(TypNarzedzia.class, new TypNarzedziaSerializer());
        gsonBuilder.registerTypeAdapter(Ulepszenie.class, new UlepszenieSerializer());
        gsonBuilder.registerTypeAdapter(TypBroni.class, new TypBroniSerializer());

        gsonBuilder.registerTypeAdapter(Efekt.class, new EfektDeserializer());
        gsonBuilder.registerTypeAdapter(Material.class, new MaterialDeserializer());
        gsonBuilder.registerTypeAdapter(RodzajItemu.class, new RodzajItemuDeserializer());
        gsonBuilder.registerTypeAdapter(Statystyka.class, new StatystykaDeserializer());
        gsonBuilder.registerTypeAdapter(TypNarzedzia.class, new TypNarzedziaDeserializer());
        gsonBuilder.registerTypeAdapter(Ulepszenie.class, new UlepszenieDeserializer());
        gsonBuilder.registerTypeAdapter(TypBroni.class, new TypBroniDeserializer());

        gson = gsonBuilder.create();
        this.file = file;
        try {
            reader = new JsonReader(new FileReader(file.getPath()));
        }
        catch(FileNotFoundException ex) {
            Bukkit.getLogger().info("Nieznaleziono Pliku Json!");
        }

        reload();
    }

    private void reload() {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                craftingManager = gson.fromJson(reader, craftingManager.getClass());
            }
            else {
                if (!file.createNewFile())
                    Bukkit.getLogger().info("[CraftingAndStats] NIE UDAŁO SIĘ STWORZYĆ PLIKU JSON");
                craftingManager = new CraftingManager();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if (craftingManager == null)
            craftingManager = new CraftingManager();
    }

    public CraftingManager getCraftingManager() {
        return craftingManager;
    }

    public Gson getGson() {
        return gson;
    }

    public void writeToJson() {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(gson.toJson(craftingManager));
            writer.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}