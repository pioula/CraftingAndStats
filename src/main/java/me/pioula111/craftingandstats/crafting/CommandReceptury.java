package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandReceptury implements CommandExecutor {
    private final static TextColor ozdobyK = TextColor.color(0x2C3394);
    private final static TextColor nazwaK = TextColor.color(0x8088FF);
    private final static TextColor rodzajK = TextColor.color(0x947B1E);
    private final static TextColor LPMK = TextColor.color(0xDECA1B);
    private CraftingManager craftingManager;

    public CommandReceptury(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Użyj /craftingi <nazwa_fachu>");
            return true;
        }

        if (!craftingManager.hasCrafting(args[0])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego craftingu!");
            return true;
        }

        WorkBench workBench = craftingManager.getCrafting(args[0]);

        sender.sendMessage(createMenu(workBench));
        return true;
    }

    private TextComponent createMenu(WorkBench workBench) {
        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(ozdobyK))
                .append(Component.text().content("Receptury craftingu " + workBench).style(Style.style(nazwaK, TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ\n").style(Style.style(ozdobyK))).build();
        int enumerator = 0;
        for (Recipe recipe : workBench.getRecipes()) {
            menu = menu.append(recipe.menuComponent(++enumerator));
        }

        return menu;
    }
}
