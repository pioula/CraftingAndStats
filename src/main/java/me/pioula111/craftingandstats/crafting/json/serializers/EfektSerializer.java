package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.itemy.napoje.Efekt;

import java.lang.reflect.Type;

public class EfektSerializer implements JsonSerializer<Efekt> {
    @Override
    public JsonElement serialize(Efekt src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        JsonObject json = new JsonObject();
        json.addProperty("typEfektu", src.getTypEfektu().getName());
        json.addProperty("czasTrwania", src.getCzasTrwania());
        json.addProperty("moc", src.getMoc());
        return json;
    }
}
