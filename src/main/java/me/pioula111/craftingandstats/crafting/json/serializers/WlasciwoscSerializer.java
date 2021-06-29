package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.itemy.wlasciwosci.Wlasciwosc;

import java.lang.reflect.Type;

public class WlasciwoscSerializer implements JsonSerializer<Wlasciwosc> {
    @Override
    public JsonElement serialize(Wlasciwosc src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        return new JsonPrimitive(src.toString());
    }
}
