package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.MenuHelper;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class CommandCraftingi implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandCraftingi(CraftingManager craftingManager) {
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

        if (!craftingManager.hasJob(args[0])) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }

         Job job = craftingManager.getJob(args[0]);

        sender.sendMessage(createMenu(job));
        return true;
    }

    private TextComponent createMenu(Job job) {
        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(MenuHelper.DECORATIONS))
                .append(Component.text().content("Craftingi fachu " + job).style(Style.style(MenuHelper.MAIN_NAME, TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ\n").style(Style.style(MenuHelper.DECORATIONS))).build();
        int enumerator = 0;
        for (WorkBench workBench : job.getWorkBenches()) {
            menu = menu.append(workBench.menuComponent(++enumerator));
        }

        return menu;
    }
}
