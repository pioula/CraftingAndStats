package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.NameSpacedKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandDestroyer implements CommandExecutor {
    private CraftingAndStats plugin;

    public CommandDestroyer(CraftingAndStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nie jesteś graczem!");
            return true;
        }

        Player player = (Player) sender;
        ItemStack destroyer = new ItemStack(Material.STICK, 1);
        ItemMeta meta = destroyer.getItemMeta();

        meta.displayName(Component.text().content("Destroyer").style(Style.style(TextColor.color(0xFF0000),TextDecoration.BOLD)).build());
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text().content("Niszczy craftingi!").style(Style.style(TextColor.color(0x009999),TextDecoration.ITALIC)).build());
        meta.lore(lore);
        meta.getPersistentDataContainer().set(NameSpacedKeys.KEY_DESTROYER, PersistentDataType.BYTE, (byte) 1);

        destroyer.setItemMeta(meta);
        player.getInventory().addItem(destroyer);
        return true;
    }
}
