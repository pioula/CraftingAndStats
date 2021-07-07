package me.pioula111.craftingandstats.crafting.json.deserializers;

import com.google.gson.*;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.items.myItems.*;

import java.lang.reflect.Type;

public class MyItemDeserializer implements JsonDeserializer<MyItem> {
    private Gson gson;

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public MyItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        switch(json.getAsJsonObject().get("type").getAsString()) {
            case "armor":
                return gson.fromJson(json, MyArmor.class);
            case "drink":
                return gson.fromJson(json, MyDrink.class);
            case "food":
                return gson.fromJson(json, MyFood.class);
            case "handcraft":
                return gson.fromJson(json, MyHandCraft.class);
            case "others":
                return gson.fromJson(json, MyOthers.class);
            case "tool":
                return gson.fromJson(json, MyTool.class);
            case "weapon":
                return gson.fromJson(json, MyWeapon.class);
            case "shield":
                return gson.fromJson(json, MyShield.class);
        }

        return null;
    }
}
