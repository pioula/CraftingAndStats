package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.items.myItems.*;

import java.lang.reflect.Type;

public class MyItemSerializer implements JsonSerializer<MyItem> {
    private Gson gson;

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public JsonElement serialize(MyItem src, Type typeOfSrc, JsonSerializationContext context) {
        switch (src.getType()) {
            case "armor":
                return gson.toJsonTree(src, MyArmor.class);
            case "drink":
                return gson.toJsonTree(src, MyDrink.class);
            case "food":
                return gson.toJsonTree(src, MyFood.class);
            case "handcraft":
                return gson.toJsonTree(src, MyHandCraft.class);
            case "others":
                return gson.toJsonTree(src, MyOthers.class);
            case "tool":
                return gson.toJsonTree(src, MyTool.class);
            case "weapon":
                return gson.toJsonTree(src, MyWeapon.class);
            case "shield":
                return gson.toJsonTree(src, MyShield.class);
        }
        return null;
    }
}
