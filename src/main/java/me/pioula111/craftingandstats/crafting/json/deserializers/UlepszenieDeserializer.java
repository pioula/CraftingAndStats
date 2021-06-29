package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.ulepszenia.Ulepszenie;

import java.lang.reflect.Type;

public class UlepszenieDeserializer implements JsonDeserializer<Ulepszenie> {
    @Override
    public Ulepszenie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return Ulepszenie.serialize(jsn.getAsString());
    }
}
