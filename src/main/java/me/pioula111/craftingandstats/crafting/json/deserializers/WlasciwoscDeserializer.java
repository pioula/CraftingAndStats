package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.Wlasciwosc;
import me.pioula111.craftingandstats.itemy.wlasciwosci.ulepszenia.Ulepszenie;

import java.lang.reflect.Type;

public class WlasciwoscDeserializer implements JsonDeserializer<Wlasciwosc> {
    @Override
    public Wlasciwosc deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return Wlasciwosc.deserialize(jsn.getAsString());
    }
}
