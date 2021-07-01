package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.items.properites.drinks.Effect;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.lang.reflect.Type;

public class EffectDeserializer implements JsonDeserializer<Effect> {
    @Override
    public Effect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsn = json.getAsJsonObject();
        return new Effect(PotionEffectTypeWrapper.getByName(jsn.get("effectType").getAsString()), jsn.get("time").getAsInt(), jsn.get("level").getAsInt());
    }
}
