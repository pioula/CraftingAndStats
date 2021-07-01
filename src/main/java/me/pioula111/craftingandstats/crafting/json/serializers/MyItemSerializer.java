package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.items.myItems.*;

import java.lang.reflect.Type;

public class MyItemSerializer implements JsonSerializer<MyItem> {
    private CraftingJsonManager jsonManager;

    public MyItemSerializer(CraftingJsonManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    @Override
    public JsonElement serialize(MyItem src, Type typeOfSrc, JsonSerializationContext context) {
        switch (src.getType()) {
            case "armor":
                return jsonManager.getGson().toJsonTree(src, MyArmor.class);
            case "drink":
                return jsonManager.getGson().toJsonTree(src, MyDrink.class);
            case "food":
                return jsonManager.getGson().toJsonTree(src, MyFood.class);
            case "handcraft":
                return jsonManager.getGson().toJsonTree(src, MyHandCraft.class);
            case "others":
                return jsonManager.getGson().toJsonTree(src, MyOthers.class);
            case "tool":
                return jsonManager.getGson().toJsonTree(src, MyTool.class);
            case "weapon":
                return jsonManager.getGson().toJsonTree(src, MyWeapon.class);
        }
        return null;
    }
}
