package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.items.properites.drinks.Effect;

import java.lang.reflect.Type;

public class EfektSerializer implements JsonSerializer<Effect> {
    @Override
    public JsonElement serialize(Effect src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null)
            return null;

        JsonObject json = new JsonObject();
        json.addProperty("typEfektu", src.getEffectType().getName());
        json.addProperty("czasTrwania", src.getTime());
        json.addProperty("moc", src.getLevel());
        return json;
    }
}
