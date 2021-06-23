package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPostawCrafting implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandPostawCrafting(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nie jesteś graczem!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Niepoprawna komenda!");
            return true;
        }

        if (!(craftingManager.hasCrafting(args[0]))) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego craftingu!");
            return true;
        }

        Player player = (Player) sender;
        Location spawnPoint = player.getLocation();

        ArmorStand workBench = (ArmorStand) spawnPoint.getWorld().spawnEntity(spawnPoint, EntityType.ARMOR_STAND);
        workBench.setSmall(true);
        workBench.setInvisible(true);
        workBench.setPersistent(true);
        workBench.setInvulnerable(true);
        workBench.setCollidable(false);
        workBench.setCustomName(args[0]);
        workBench.setCustomNameVisible(true);

        return true;
    }
}
