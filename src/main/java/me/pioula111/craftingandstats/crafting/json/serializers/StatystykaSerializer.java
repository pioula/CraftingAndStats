package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.statystyki.Statystyka;

import java.lang.reflect.Type;

public class StatystykaSerializer implements JsonSerializer<Statystyka> {
    @Override
    public JsonElement serialize(Statystyka src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        return new JsonPrimitive(src.toString());
    }
}
