package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.narzedzia.TypNarzedzia;

import java.lang.reflect.Type;

public class TypNarzedziaSerializer implements JsonSerializer<TypNarzedzia> {
    @Override
    public JsonElement serialize(TypNarzedzia src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        return new JsonPrimitive(src.toString());
    }
}
