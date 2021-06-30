package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.items.properites.Property;

import java.lang.reflect.Type;

public class WlasciwoscDeserializer implements JsonDeserializer<Property> {
    @Override
    public Property deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsn = json.getAsJsonPrimitive();
        return Property.deserialize(jsn.getAsString());
    }
}
