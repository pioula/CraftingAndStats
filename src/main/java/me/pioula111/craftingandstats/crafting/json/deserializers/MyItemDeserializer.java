package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.items.myItems.*;

import java.lang.reflect.Type;

public class MyItemDeserializer implements JsonDeserializer<MyItem> {
    private CraftingJsonManager jsonManager;

    public MyItemDeserializer(CraftingJsonManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    @Override
    public MyItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        switch(json.getAsJsonObject().get("type").getAsString()) {
            case "armor":
                return jsonManager.getGson().fromJson(json, MyArmor.class);
            case "drink":
                return jsonManager.getGson().fromJson(json, MyDrink.class);
            case "food":
                return jsonManager.getGson().fromJson(json, MyFood.class);
            case "handcraft":
                return jsonManager.getGson().fromJson(json, MyHandCraft.class);
            case "others":
                return jsonManager.getGson().fromJson(json, MyOthers.class);
            case "tool":
                return jsonManager.getGson().fromJson(json, MyTool.class);
            case "weapon":
                return jsonManager.getGson().fromJson(json, MyWeapon.class);
        }

        return null;
    }
}
