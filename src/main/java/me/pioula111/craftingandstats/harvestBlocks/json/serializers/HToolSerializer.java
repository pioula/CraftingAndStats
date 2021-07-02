package me.pioula111.craftingandstats.harvestBlocks.json.serializers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.harvestBlocks.harvestTools.*;

import java.lang.reflect.Type;

public class HToolSerializer implements JsonSerializer<HTool> {
    private Gson gson;

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public JsonElement serialize(HTool src, Type typeOfSrc, JsonSerializationContext context) {
        switch (src.getName()) {
            case "pickaxe":
                return gson.toJsonTree(src, HPickaxe.class);
            case "axe":
                return gson.toJsonTree(src, HAxe.class);
            case "scythe":
                return gson.toJsonTree(src, HScythe.class);
            case "sickle":
                return gson.toJsonTree(src, HSickle.class);
        }
        return null;
    }
}
