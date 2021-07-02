package me.pioula111.craftingandstats.harvestBlocks.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.harvestBlocks.harvestTools.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Type;

public class HToolDeserializer implements JsonDeserializer<HTool> {
    private Gson gson;

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public HTool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CraftingAndStats plugin = JavaPlugin.getPlugin(CraftingAndStats.class);
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        switch(json.getAsJsonObject().get("name").getAsString()) {
            case "pickaxe": {
                HTool tmp = gson.fromJson(json, HPickaxe.class);
                pluginManager.registerEvents(tmp, plugin);
                return tmp;
            }
            case "scythe": {
                HTool tmp = gson.fromJson(json, HScythe.class);
                pluginManager.registerEvents(tmp, plugin);
                return tmp;
            }
            case "axe": {
                HTool tmp = gson.fromJson(json, HAxe.class);
                pluginManager.registerEvents(tmp, plugin);
                return tmp;
            }
            case "sickle": {
                HTool tmp = gson.fromJson(json, HSickle.class);
                pluginManager.registerEvents(tmp, plugin);
                return tmp;
            }
        }

        return null;
    }
}
