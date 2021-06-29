package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.itemy.bronie.TypBroni;

import java.lang.reflect.Type;

public class TypBroniSerializer implements JsonSerializer<TypBroni> {
    @Override
    public JsonElement serialize(TypBroni src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        return new JsonPrimitive(src.toString());
    }
}
