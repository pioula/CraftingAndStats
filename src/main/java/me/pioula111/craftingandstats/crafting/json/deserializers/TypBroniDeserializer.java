package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.bronie.TypBroni;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;

import java.lang.reflect.Type;

public class TypBroniDeserializer implements JsonDeserializer<TypBroni> {
    @Override
    public TypBroni deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return TypBroni.serialize(jsn.getAsString());
    }
}
