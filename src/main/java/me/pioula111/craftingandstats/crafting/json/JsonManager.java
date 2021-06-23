package me.pioula111.craftingandstats.crafting.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class JsonManager {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;
    private File file;
    private JsonReader reader;
    private CraftingManager craftingManager;

    public JsonManager(File file) {
        craftingManager = new CraftingManager();
        gsonBuilder.setPrettyPrinting();

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
    }

    public CraftingManager getPlayerDataScores() {
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
            System.out.println("[CraftingAndStats] Blad");
        }
    }
}