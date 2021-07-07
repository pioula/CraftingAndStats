package me.pioula111.craftingandstats.thievery.keyLocking.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.crafting.json.deserializers.EffectDeserializer;
import me.pioula111.craftingandstats.crafting.json.deserializers.MaterialDeserializer;
import me.pioula111.craftingandstats.crafting.json.deserializers.MyItemDeserializer;
import me.pioula111.craftingandstats.crafting.json.deserializers.PropertyDeserializer;
import me.pioula111.craftingandstats.crafting.json.serializers.EffectSerializer;
import me.pioula111.craftingandstats.crafting.json.serializers.MaterialSerializer;
import me.pioula111.craftingandstats.crafting.json.serializers.MyItemSerializer;
import me.pioula111.craftingandstats.crafting.json.serializers.PropertySerializer;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.properites.Property;
import me.pioula111.craftingandstats.items.properites.drinks.Effect;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class LootJsonManager {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;
    private File file;
    private JsonReader reader;
    private LootManager lootManager;

    public LootJsonManager(File file) {
        gsonBuilder.setPrettyPrinting();
        lootManager = new LootManager();

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
                lootManager = gson.fromJson(reader, lootManager.getClass());
            }
            else {
                if (!file.createNewFile())
                    Bukkit.getLogger().info("[CraftingAndStats] NIE UDAŁO SIĘ STWORZYĆ PLIKU JSON");
                lootManager = new LootManager();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if (lootManager == null)
            lootManager = new LootManager();
    }

    public LootManager getLootManager() {
        return lootManager;
    }

    public Gson getGson() {
        return gson;
    }

    public void writeToJson() {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(gson.toJson(lootManager));
            writer.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}