package me.pioula111.craftingandstats.crafting.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.pioula111.craftingandstats.crafting.json.deserializers.*;
import me.pioula111.craftingandstats.crafting.json.serializers.*;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.properites.Property;
import me.pioula111.craftingandstats.items.properites.drinks.Effect;
import org.bukkit.Bukkit;
import org.bukkit.Material;

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

        MyItemSerializer myItemSerializer = new MyItemSerializer();
        MyItemDeserializer myItemDeserializer = new MyItemDeserializer();

        gsonBuilder.registerTypeAdapter(Effect.class, new EffectSerializer());
        gsonBuilder.registerTypeAdapter(Material.class, new MaterialSerializer());
        gsonBuilder.registerTypeAdapter(Property.class, new PropertySerializer());
        gsonBuilder.registerTypeAdapter(MyItem.class, myItemSerializer);

        gsonBuilder.registerTypeAdapter(Effect.class, new EffectDeserializer());
        gsonBuilder.registerTypeAdapter(Material.class, new MaterialDeserializer());
        gsonBuilder.registerTypeAdapter(Property.class, new PropertyDeserializer());
        gsonBuilder.registerTypeAdapter(MyItem.class, myItemDeserializer);

        gson = gsonBuilder.create();
        myItemDeserializer.setGson(gson);
        myItemSerializer.setGson(gson);

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
                    Bukkit.getLogger().info("[CraftingAndStats] NIE UDA??O SI?? STWORZY?? PLIKU JSON");
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