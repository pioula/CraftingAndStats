package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.*;
import org.bukkit.Material;

import java.lang.reflect.Type;

public class MaterialSerializer implements JsonSerializer<Material> {
    @Override
    public JsonElement serialize(Material src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null)
            return null;

        return new JsonPrimitive(src.name());
    }
}
