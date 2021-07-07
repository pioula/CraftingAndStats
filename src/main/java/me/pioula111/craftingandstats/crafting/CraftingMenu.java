package me.pioula111.craftingandstats.crafting;

import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.GuiElementGroup;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.DropItemHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.crafting.Ingredient;
import me.pioula111.craftingandstats.crafting.Recipe;
import me.pioula111.craftingandstats.gui.ComponentWrapper;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CraftingMenu {
    private GuiElementGroup[] firstRow;
    private GuiElementGroup[] secondRow;
    private GuiElementGroup[] thirdRow;
    private InventoryGui gui;
    private final String[] guiSetup = {
            " 0123456 ",
            "<abcdefg>",
            " ABCDEFG "
    };

    public CraftingMenu(String title) {
        firstRow = new GuiElementGroup[7];
        secondRow = new GuiElementGroup[7];
        thirdRow = new GuiElementGroup[7];

        for (int i = 0; i < firstRow.length; i++) {
            firstRow[i] = new GuiElementGroup((char)(i + '0'));
            secondRow[i] = new GuiElementGroup((char)(i + 'a'));
            thirdRow[i] = new GuiElementGroup((char)(i + 'A'));
        }

        gui = new InventoryGui(JavaPlugin.getPlugin(CraftingAndStats.class), GuiHelper.createGuiTitle(title), guiSetup);

        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(GuiHelper.rightArrow('>'));
        gui.addElement(GuiHelper.leftArrow('<'));
    }

    public void setRecipes(List<Recipe> recipes, Player player) {
        Iterator<Recipe> it = recipes.listIterator();
        for (int i = 0; i < recipes.size(); i++) {
            int ind = i % 7;
            Recipe recipe = it.next();
            ItemStack result = recipe.getResult().getIngredient().makeItem(recipe.getResult().getAmount());
            firstRow[ind].addElement(new StaticGuiElement((char)(ind + '0'), result));

            ItemStack ingredients = createIngredients(recipe);
            secondRow[ind].addElement(new StaticGuiElement((char)(ind + 'a'), ingredients));

            char tmp = (char)(ind + 'A');
            thirdRow[ind].addElement(new DynamicGuiElement(tmp,
                    (viewer) -> new StaticGuiElement(tmp, craftIcon(recipe, player),
                            click -> {
                                if (checkIngredients(recipe.getIngredients(), player.getInventory()).size() == 0) {
                                    craftItem(recipe, player);
                                }
                                click.getGui().draw();
                                return true;
                            })));
        }

        addEmptyColumns(7 - (recipes.size() % 7));
    }

    private void addEmptyColumns(int emptyCols) {
        for (int i = 0; i < emptyCols; i++) {
            firstRow[6 - i].addElement(new StaticGuiElement((char)(6 - i + '0'), GuiHelper.getFiller()));
            secondRow[6 - i].addElement(new StaticGuiElement((char)(6 - i + 'a'), GuiHelper.getFiller()));
            thirdRow[6 - i].addElement(new StaticGuiElement((char)(6 - i + 'A'), GuiHelper.getFiller()));
        }
    }

    private void craftItem(Recipe recipe, Player player) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            int amount = ingredient.getAmount();
            for (ItemStack content : player.getInventory().getContents()) {
                if (areSimilar(ingredient.getIngredient(), content)) {
                    int removedItems = Math.min(amount, content.getAmount());
                    content.setAmount(content.getAmount() - removedItems);
                    amount -= removedItems;
                    if (content.getAmount() == 0)
                        player.getInventory().removeItem(content);
                }
                if (amount == 0)
                    break;
            }
        }

        DropItemHelper.dropItem(player, recipe.getResult().getIngredient().makeItem(recipe.getResult().getAmount()));
    }

    private ItemStack craftIcon(Recipe recipe, Player player) {
        ArrayList<Ingredient> lackingIngredients = checkIngredients(recipe.getIngredients(), player.getInventory());
        ItemStack item = new ItemStack(Material.SLIME_BALL);
        ItemMeta meta = item.getItemMeta();

        if (lackingIngredients.size() == 0) {
            meta.displayName(ComponentWrapper.itemName("Stwórz przedmiot"));
            item.setItemMeta(meta);
        }
        else {
            List<Component> lore = new ArrayList<>();

            meta.displayName(ComponentWrapper.itemName("Nie możesz stworzyć tego przedmiotu"));
            lore.add(ComponentWrapper.lore("Brakujące składniki:"));

            for (Ingredient lackingIngredient : lackingIngredients) {
                lore.add(ComponentWrapper.lore(lackingIngredient.toString()));
            }

            item.setItemMeta(meta);
            item.lore(lore);
        }

        return item;
    }

    private ArrayList<Ingredient> checkIngredients(ArrayList<Ingredient> ingredients, PlayerInventory inventory) {
        ArrayList<Ingredient> lackingIngrednients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            int amount = ingredient.getAmount();
            for (ItemStack content : inventory.getContents()) {
                if (areSimilar(ingredient.getIngredient(), content)) {
                    amount -= content.getAmount();
                }
            }
            if (amount > 0) {
                lackingIngrednients.add(new Ingredient(ingredient.getIngredient(), amount));
            }
        }

        return lackingIngrednients;
    }

    private boolean areSimilar(MyItem ingredient, ItemStack content) {
        return content != null && content.hasItemMeta() &&
                content.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                content.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(ingredient.getName());
    }

    private ItemStack createIngredients(Recipe recipe) {
        ItemStack item = new ItemStack(Material.SLIME_BALL, 1);
        ItemMeta itemMeta = item.getItemMeta();

        List<Component> lore = new ArrayList<>();

        itemMeta.displayName(ComponentWrapper.itemName("Potrzebne Materiały: " + recipe.toString()));
        for (Ingredient ingredient : recipe.getIngredients()) {
            lore.add(ComponentWrapper.lore(ingredient.toString()));
        }

        item.setItemMeta(itemMeta);
        item.lore(lore);
        return item;
    }

    public void showToPlayer(Player player) {
        for (int i = 0; i < firstRow.length; i++) {
            gui.addElement(firstRow[i]);
            gui.addElement(secondRow[i]);
            gui.addElement(thirdRow[i]);
        }

        gui.show(player);
    }
}
