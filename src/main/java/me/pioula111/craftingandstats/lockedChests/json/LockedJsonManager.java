package me.pioula111.craftingandstats.lockedChests.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class LockedJsonManager {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;
    private File file;
    private JsonReader reader;
    private LockedManager lockedManager;

    public LockedJsonManager(File file) {
        gsonBuilder.setPrettyPrinting();
        lockedManager = new LockedManager();

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
                lockedManager = gson.fromJson(reader, lockedManager.getClass());
            }
            else {
                if (!file.createNewFile())
                    Bukkit.getLogger().info("[CraftingAndStats] NIE UDAŁO SIĘ STWORZYĆ PLIKU JSON");
                lockedManager = new LockedManager();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if (lockedManager == null)
            lockedManager = new LockedManager();
    }

    public LockedManager getLockedManager() {
        return lockedManager;
    }

    public Gson getGson() {
        return gson;
    }

    public void writeToJson() {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(gson.toJson(lockedManager));
            writer.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
