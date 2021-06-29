package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.rodzaje.RodzajItemu;
import org.bukkit.Material;

import java.lang.reflect.Type;

public class RodzajItemuDeserializer implements JsonDeserializer<RodzajItemu> {
    @Override
    public RodzajItemu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return RodzajItemu.serialize(jsn.getAsString());
    }
}
