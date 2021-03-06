package me.pioula111.craftingandstats.pvpAndPve.death;

import me.pioula111.craftingandstats.ItemHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CommandSearch implements CommandExecutor {
    private DeathManager deathManager;
    private HashMap<String, Long> lastSearch;
    private static final Random r = new Random();
    private static final long THREE_SECONDS = 3000;

    public CommandSearch(DeathManager deathManager) {
        this.deathManager = deathManager;
        lastSearch = new HashMap<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Napisz /przeszukaj");
            return true;
        }

        Player player = (Player) sender;
        if (!canSearch(player)) {
            sender.sendMessage(ChatColor.RED + "Nie możesz tak szybko przeszukiwać! Odczekaj 3 sekundy!");
            return true;
        }

        boolean foundDeadPlayer = false;
        List<Entity> nearbyEntities = player.getNearbyEntities(2, 1, 2);

        for (Entity nearbyEntity : nearbyEntities) {
            if (nearbyEntity instanceof Player && !player.equals(nearbyEntity) && deathManager.playerIsDead((Player) nearbyEntity)) {
                if (hasFound()) {
                    Player deadPlayer = (Player) nearbyEntity;
                    deadPlayer.sendMessage(ChatColor.RED + "Czujesz, że ktoś grzebie ci po kieszeniach...");
                    ArrayList<ItemStack> contents = getRealContents(deadPlayer.getInventory().getContents(), deadPlayer);
                    if (contents.size() == 0) {
                        player.sendMessage(ChatColor.RED + "Nic przy sobie nie ma!");
                        foundDeadPlayer = true;
                        break;
                    }

                    int ind = Math.abs(r.nextInt()) % contents.size();
                    ItemStack foundItem = contents.get(ind).clone();
                    deadPlayer.getInventory().removeItem(contents.get(ind));
                    ItemHelper.dropItem(player, foundItem);
                }
                else {
                    player.sendMessage(ChatColor.RED + "Nic nie znaleziono!");
                }
                foundDeadPlayer = true;
                break;
            }
        }
        if (!foundDeadPlayer) {
            player.sendMessage(ChatColor.RED + "Nie ma nikogo nieprzytomnego w pobliżu!");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Przeszukanie zakończone!");
        return true;
    }

    private boolean canSearch(Player player) {
        if (!lastSearch.containsKey(player.getName()) || lastSearch.get(player.getName()) + THREE_SECONDS <= System.currentTimeMillis()) {
            lastSearch.put(player.getName(), System.currentTimeMillis());
            return true;
        }

        return false;
    }

    private ArrayList<ItemStack> getRealContents(ItemStack[] contents, Player deadPlayer) {
        ArrayList<ItemStack> realContents = new ArrayList<>();
        for (ItemStack content : contents) {
            if (content != null && content.getAmount() != 0 && !deadPlayer.getInventory().getItemInOffHand().equals(content) && !isArmor(content, deadPlayer)) {
                realContents.add(content);
            }
        }
        return realContents;
    }

    private boolean isArmor(ItemStack content, Player deadPlayer) {
        for (ItemStack armorContent : deadPlayer.getInventory().getArmorContents()) {
            if (armorContent != null && armorContent.equals(content))
                return true;
        }

        return false;
    }

    private boolean hasFound() {
        return r.nextBoolean();
    }
}
