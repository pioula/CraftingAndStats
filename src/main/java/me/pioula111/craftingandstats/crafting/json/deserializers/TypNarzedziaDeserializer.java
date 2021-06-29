package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.narzedzia.TypNarzedzia;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;

import java.lang.reflect.Type;

public class TypNarzedziaDeserializer implements JsonDeserializer<TypNarzedzia> {
    @Override
    public TypNarzedzia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return TypNarzedzia.serialize(jsn.getAsString());
    }
}
