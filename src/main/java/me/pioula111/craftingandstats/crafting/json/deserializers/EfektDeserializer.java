package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.napoje.Efekt;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.lang.reflect.Type;

public class EfektDeserializer implements JsonDeserializer<Efekt> {
    @Override
    public Efekt deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsn = json.getAsJsonObject();
        return new Efekt(PotionEffectTypeWrapper.getByName(jsn.get("typEfektu").getAsString()), jsn.get("czasTrwania").getAsInt(), jsn.get("moc").getAsInt());
    }
}
