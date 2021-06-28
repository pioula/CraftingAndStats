package me.pioula111.craftingandstats.crafting.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Type;

public class ItemStackSerializer implements JsonSerializer<ItemStack> {
    private CraftingJsonManager jsonManager;

    public ItemStackSerializer(CraftingJsonManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null)
            return null;

        PersistentDataContainer pdc = src.getItemMeta().getPersistentDataContainer();

        JsonObject json = new JsonObject();
        for (NamespacedKey key : pdc.getKeys()) {
            switch (key.getKey()) {
                case "destroyer":
                    json.addProperty(key.getKey(), pdc.get(key, PersistentDataType.BYTE));
                    break;
                case "dmg":
                    json.addProperty(key.getKey(), pdc.get(key, PersistentDataType.INTEGER));
                    break;
                case "wielkoscStatystyki":
                    json.addProperty(key.getKey(), pdc.get(key, PersistentDataType.INTEGER));
                    break;
                case "obrona":
                    json.addProperty(key.getKey(), pdc.get(key, PersistentDataType.INTEGER));
                    break;
                case "kolorNapoju":
                    json.addProperty(key.getKey(), pdc.get(key, PersistentDataType.INTEGER));
                    break;
                default:
                    json.addProperty(key.getKey(), pdc.get(key, PersistentDataType.STRING));
            }
        }
        return json;
    }
}
