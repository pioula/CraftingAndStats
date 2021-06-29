package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;

import java.lang.reflect.Type;

public class RodzajItemuSerializer implements JsonSerializer<RodzajItemu> {
    @Override
    public JsonElement serialize(RodzajItemu src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        return new JsonPrimitive(src.toString());
    }
}
