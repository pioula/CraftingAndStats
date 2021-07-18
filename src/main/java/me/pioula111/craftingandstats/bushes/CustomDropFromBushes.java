package me.pioula111.craftingandstats.bushes;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.ItemHelper;
import me.pioula111.craftingandstats.items.myItems.MyFood;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.myItems.MyOthers;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class CustomDropFromBushes implements Listener {
    private CraftingAndStats plugin;
    private static Random r = new Random();
    private final int BLACKBERRY_CHANCE = 10, BEAUTYBERRY_CHANCE = 30, RASPBERRY_CHANCE = 60;

    public CustomDropFromBushes(CraftingAndStats plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerHarvestBlock(PlayerHarvestBlockEvent event) {
        Player player = event.getPlayer();

        if (event.getHarvestedBlock().getType() == Material.SWEET_BERRY_BUSH) {
            List<ItemStack> items = event.getItemsHarvested();
            for (ItemStack itemStack : items) {
                itemStack.setAmount(0);
                int chance = r.nextInt(100) + 1;
                int bug_chance = r.nextInt(100) + 1;
                if (bug_chance <= 20) {
                    MyItem bug = new MyOthers();
                    bug.setSwappedItem(Material.SLIME_BALL);
                    bug.setName("Robak");

                    player.getWorld().dropItem(event.getHarvestedBlock().getLocation(), bug.makeItem(1));
                }
                if (chance <= BLACKBERRY_CHANCE) {
                    MyItem berry = new MyFood();
                    berry.setSwappedItem(Material.SWEET_BERRIES);
                    berry.setName(BerriesE.BLACKBERRY.toString());

                    player.getWorld().dropItem(event.getHarvestedBlock().getLocation(), berry.makeItem(1));
                }
                else if (chance <= BLACKBERRY_CHANCE + BEAUTYBERRY_CHANCE) {
                    MyItem berry = new MyFood();
                    berry.setSwappedItem(Material.SWEET_BERRIES);
                    berry.setName(BerriesE.BEAUTYBERRY.toString());

                    player.getWorld().dropItem(event.getHarvestedBlock().getLocation(), berry.makeItem(1));
                }
                else {
                    MyItem berry = new MyFood();
                    berry.setSwappedItem(Material.SWEET_BERRIES);
                    berry.setName(BerriesE.RASPBERRY.toString());

                    player.getWorld().dropItem(event.getHarvestedBlock().getLocation(), berry.makeItem(1));
                }
            }
        }
    }
}
