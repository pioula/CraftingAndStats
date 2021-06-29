package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;
import me.pioula111.craftingandstats.itemy.statystyki.Statystyka;

import java.lang.reflect.Type;

public class StatystykaDeserializer implements JsonDeserializer<Statystyka> {
    @Override
    public Statystyka deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return Statystyka.serialize(jsn.getAsString());
    }
}
