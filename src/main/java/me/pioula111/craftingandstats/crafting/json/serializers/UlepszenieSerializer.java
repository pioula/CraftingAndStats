package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.ulepszenia.Ulepszenie;

import java.lang.reflect.Type;

public class UlepszenieSerializer implements JsonSerializer<Ulepszenie> {
    @Override
    public JsonElement serialize(Ulepszenie src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        return new JsonPrimitive(src.toString());
    }
}
