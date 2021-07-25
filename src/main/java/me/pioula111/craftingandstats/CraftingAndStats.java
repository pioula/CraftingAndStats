package me.pioula111.craftingandstats;

import me.pioula111.craftingandstats.bushes.*;
import me.pioula111.craftingandstats.crafting.*;
import me.pioula111.craftingandstats.crafting.json.CraftingJsonManager;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.fishingAndBoats.boats.BoatPlacing;
import me.pioula111.craftingandstats.fishingAndBoats.boats.BoatRemoving;
import me.pioula111.craftingandstats.fishingAndBoats.boats.PreventBoatDestroying;
import me.pioula111.craftingandstats.fishingAndBoats.fishing.CommandFishChances;
import me.pioula111.craftingandstats.fishingAndBoats.fishing.CommandFishingLoot;
import me.pioula111.craftingandstats.fishingAndBoats.fishing.CommandPlaceFishery;
import me.pioula111.craftingandstats.fishingAndBoats.fishing.PlayerFishing;
import me.pioula111.craftingandstats.harvestBlocks.BlockSheduler;
import me.pioula111.craftingandstats.harvestBlocks.CommandAddBlock;
import me.pioula111.craftingandstats.harvestBlocks.CommandBlocks;
import me.pioula111.craftingandstats.harvestBlocks.InteractionEventCatcher;
import me.pioula111.craftingandstats.harvestBlocks.json.HarvestJsonManager;
import me.pioula111.craftingandstats.harvestBlocks.json.HarvestManager;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.additionCommands.CommandNone;
import me.pioula111.craftingandstats.items.commands.additionCommands.CommandUpgrade;
import me.pioula111.craftingandstats.items.commands.drinkCommands.CommandDrink;
import me.pioula111.craftingandstats.items.commands.drinkCommands.CommandDrinkColor;
import me.pioula111.craftingandstats.items.commands.drinkCommands.CommandEffectList;
import me.pioula111.craftingandstats.items.commands.drinkCommands.CommandEffects;
import me.pioula111.craftingandstats.items.commands.mainCommands.CommandCreateItem;
import me.pioula111.craftingandstats.items.commands.simpleItemsCommands.CommandFood;
import me.pioula111.craftingandstats.items.commands.simpleItemsCommands.CommandHandCraft;
import me.pioula111.craftingandstats.items.commands.simpleItemsCommands.CommandOthers;
import me.pioula111.craftingandstats.items.commands.simpleItemsCommands.CommandShield;
import me.pioula111.craftingandstats.items.commands.toolsCommands.*;
import me.pioula111.craftingandstats.items.commands.weaponsCommands.*;
import me.pioula111.craftingandstats.itemy.komendy.komendyBroni.CommandOneHanded;
import me.pioula111.craftingandstats.itemy.komendy.komendyInne.CommandStopCreatingItem;
import me.pioula111.craftingandstats.items.commands.armorCommands.CommandDefence;
import me.pioula111.craftingandstats.items.commands.armorCommands.CommandArmor;
import me.pioula111.craftingandstats.lockedChests.*;
import me.pioula111.craftingandstats.lockedChests.json.LockedJsonManager;
import me.pioula111.craftingandstats.lockedChests.json.LockedManager;
import me.pioula111.craftingandstats.markers.Marker;
import me.pioula111.craftingandstats.npcs.BlockNpcPickup;
import me.pioula111.craftingandstats.pvpAndPve.NPCVanillaDamageStopper;
import me.pioula111.craftingandstats.pvpAndPve.PlayerAttack;
import me.pioula111.craftingandstats.pvpAndPve.PlayerHpIncrease;
import me.pioula111.craftingandstats.pvpAndPve.TwoHandedWeapons;
import me.pioula111.craftingandstats.pvpAndPve.death.CommandSearch;
import me.pioula111.craftingandstats.pvpAndPve.death.DeathManager;
import me.pioula111.craftingandstats.pvpAndPve.death.PlayerDeath;
import me.pioula111.craftingandstats.shoping.CommandAddGood;
import me.pioula111.craftingandstats.shoping.CommandPlaceShop;
import me.pioula111.craftingandstats.shoping.UsingShop;
import me.pioula111.craftingandstats.shoping.json.ShopJsonManager;
import me.pioula111.craftingandstats.shoping.json.ShopManager;
import me.pioula111.craftingandstats.stats.CommandStaty;
import me.pioula111.craftingandstats.stats.HealthManager;
import me.pioula111.craftingandstats.stats.json.StatJsonOnJoin;
import me.pioula111.craftingandstats.stats.json.StatJsonOnQuit;
import me.pioula111.craftingandstats.stats.json.StatManager;
import me.pioula111.craftingandstats.thievery.keyLocking.*;
import me.pioula111.craftingandstats.thievery.keyLocking.json.LootJsonManager;
import me.pioula111.craftingandstats.thievery.keyLocking.json.LootManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class CraftingAndStats extends JavaPlugin {
    private File craftingJsonFile, harvestJsonFile, lockpickJsonFile, fishingJsonFile, shopJsonFile, keysJsonFile;
    private NameSpacedKeys nameSpacedKeys;
    private StatManager statManager;
    private ShopJsonManager shopJsonManager;
    private CraftingJsonManager craftingJsonManager;
    private HarvestJsonManager harvestJsonManager;
    private LootJsonManager lockpickJsonManager, fishingJsonManager;
    private BlockSheduler blockSheduler;
    private LockedJsonManager lockedJsonManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        craftingJsonFile = new File("plugins/CraftingAndStats/crafting_and_stats.json");
        craftingJsonManager = new CraftingJsonManager(craftingJsonFile);
        CraftingManager craftingManager = craftingJsonManager.getCraftingManager();

        harvestJsonFile = new File("plugins/CraftingAndStats/harvest.json");
        harvestJsonManager = new HarvestJsonManager(harvestJsonFile);
        HarvestManager harvestManager = harvestJsonManager.getHarvestManager();

        lockpickJsonFile = new File("plugins/CraftingAndStats/lockpick.json");
        lockpickJsonManager = new LootJsonManager(lockpickJsonFile);
        LootManager lockpickManager = lockpickJsonManager.getLootManager();

        fishingJsonFile = new File("plugins/CraftingAndStats/fishing.json");
        fishingJsonManager = new LootJsonManager(fishingJsonFile);
        LootManager fishingManager = fishingJsonManager.getLootManager();

        shopJsonFile = new File("plugins/CraftingAndStats/shop.json");
        shopJsonManager = new ShopJsonManager(shopJsonFile);
        ShopManager shopManager = shopJsonManager.getShopManager();

        keysJsonFile = new File("plugins/CraftingAndStats/keys.json");
        lockedJsonManager = new LockedJsonManager(keysJsonFile);
        LockedManager lockedManager = lockedJsonManager.getLockedManager();

        blockSheduler = new BlockSheduler(this);

        nameSpacedKeys = new NameSpacedKeys(this);
        statManager = new StatManager();

        DeathManager deathManager = new DeathManager();

        ChestManager chestManager = new ChestManager();

        TeachJobManager teachJobManager = new TeachJobManager(statManager, this);

        PluginManager pluginManager = getServer().getPluginManager();

        TwoHandedWeapons sheduler = new TwoHandedWeapons(this);
        sheduler.shedule();

        pluginManager.registerEvents(new KeyManager(lockedManager), this);
        pluginManager.registerEvents(new RemovingOpenerManager(lockedManager), this);

        AddingOwnerManager addingOwnerManager = new AddingOwnerManager(lockedManager);
        pluginManager.registerEvents(addingOwnerManager, this);

        pluginManager.registerEvents(new HealthManager(statManager), this);

        pluginManager.registerEvents(new PlayerDeath(deathManager, this), this);
        pluginManager.registerEvents(deathManager, this);

        pluginManager.registerEvents(new UsingAndRemovingWorkBench(craftingManager, this, statManager), this);
        pluginManager.registerEvents(teachJobManager, this);
        pluginManager.registerEvents(new Marker(), this);

        pluginManager.registerEvents(new StatJsonOnJoin(statManager), this);
        pluginManager.registerEvents(new StatJsonOnQuit(statManager), this);

        pluginManager.registerEvents(new InteractionEventCatcher(harvestManager), this);

        pluginManager.registerEvents(new PlayerAttack(statManager, deathManager), this);
        pluginManager.registerEvents(new BlockNpcPickup(), this);
        pluginManager.registerEvents(new NPCVanillaDamageStopper(), this);
        pluginManager.registerEvents(new PlayerHpIncrease(statManager, deathManager), this);

        pluginManager.registerEvents(new OpeningChest(statManager, chestManager, lockpickManager), this);
        pluginManager.registerEvents(lockpickManager, this);

        pluginManager.registerEvents(fishingManager, this);
        pluginManager.registerEvents(new BoatPlacing(), this);
        pluginManager.registerEvents(new PreventBoatDestroying(), this);
        pluginManager.registerEvents(new BoatRemoving(), this);
        pluginManager.registerEvents(new PlayerFishing(fishingManager), this);

        pluginManager.registerEvents(new UsingShop(shopManager, this), this);

        pluginManager.registerEvents(new NoDamageFromBush(), this);
        pluginManager.registerEvents(new BlackberryProperty(), this);
        pluginManager.registerEvents(new BeautyberryProperty(), this);
        pluginManager.registerEvents(new RaspberryProperty(), this);
        pluginManager.registerEvents(new CustomDropFromBushes(this), this);

        Objects.requireNonNull(this.getCommand("bloki")).setExecutor(new CommandBlocks(harvestManager));
        Objects.requireNonNull(this.getCommand("dodajblok")).setExecutor(new CommandAddBlock(harvestManager));

        ItemManager itemManager = new ItemManager();
        Objects.requireNonNull(this.getCommand("stworzitem")).setExecutor(new CommandCreateItem(itemManager));

        Objects.requireNonNull(this.getCommand("handcraft")).setExecutor(new CommandHandCraft(itemManager));
        Objects.requireNonNull(this.getCommand("food")).setExecutor(new CommandFood(itemManager));
        Objects.requireNonNull(this.getCommand("others")).setExecutor(new CommandOthers(itemManager));
        Objects.requireNonNull(this.getCommand("weapon")).setExecutor(new CommandWeapon(itemManager));
        Objects.requireNonNull(this.getCommand("shield")).setExecutor(new CommandShield(itemManager));

        Objects.requireNonNull(this.getCommand("one_handed")).setExecutor(new CommandOneHanded(itemManager));
        Objects.requireNonNull(this.getCommand("two_handed")).setExecutor(new CommandTwoHanded(itemManager));
        Objects.requireNonNull(this.getCommand("long_distance")).setExecutor(new CommandLongDistance(itemManager));

        Objects.requireNonNull(this.getCommand("dmg")).setExecutor(new CommandDmg(itemManager));
        Objects.requireNonNull(this.getCommand("dexterity")).setExecutor(new CommandDexterity(itemManager));
        Objects.requireNonNull(this.getCommand("strength")).setExecutor(new CommandStrength(itemManager));

        Objects.requireNonNull(this.getCommand("statystyka")).setExecutor(new CommandStatistic(itemManager));
        Objects.requireNonNull(this.getCommand("none")).setExecutor(new CommandNone(itemManager));
        Objects.requireNonNull(this.getCommand("upgrade")).setExecutor(new CommandUpgrade(itemManager));

        Objects.requireNonNull(this.getCommand("obrona")).setExecutor(new CommandDefence(itemManager));
        Objects.requireNonNull(this.getCommand("armor")).setExecutor(new CommandArmor(itemManager));

        Objects.requireNonNull(this.getCommand("tool")).setExecutor(new CommandTool(itemManager));
        Objects.requireNonNull(this.getCommand("pickaxe")).setExecutor(new CommandPickaxe(itemManager));
        Objects.requireNonNull(this.getCommand("scythe")).setExecutor(new CommandScythe(itemManager));
        Objects.requireNonNull(this.getCommand("fishing_rod")).setExecutor(new CommandFishingRod(itemManager));
        Objects.requireNonNull(this.getCommand("axe")).setExecutor(new CommandAxe(itemManager));
        Objects.requireNonNull(this.getCommand("sickle")).setExecutor(new CommandSickle(itemManager));

        Objects.requireNonNull(this.getCommand("drink")).setExecutor(new CommandDrink(itemManager));
        Objects.requireNonNull(this.getCommand("efekty")).setExecutor(new CommandEffects(itemManager));
        Objects.requireNonNull(this.getCommand("kolornapoju")).setExecutor(new CommandDrinkColor(itemManager));
        Objects.requireNonNull(this.getCommand("listaefektow")).setExecutor(new CommandEffectList());

        Objects.requireNonNull(this.getCommand("przerwijrobienieitemu")).setExecutor(new CommandStopCreatingItem(itemManager));

        Objects.requireNonNull(this.getCommand("staty")).setExecutor(new CommandStaty(this));

        Objects.requireNonNull(this.getCommand("akceptujnauke")).setExecutor(new CommandAcceptJob(statManager, teachJobManager));
        Objects.requireNonNull(this.getCommand("craftingi")).setExecutor(new CommandCraftings(craftingManager));
        Objects.requireNonNull(this.getCommand("destroyer")).setExecutor(new CommandDestroyer(this));
        Objects.requireNonNull(this.getCommand("fachy")).setExecutor(new CommandJobs(craftingManager));
        Objects.requireNonNull(this.getCommand("nowyfach")).setExecutor(new CommandNewJob(craftingManager));
        Objects.requireNonNull(this.getCommand("nowareceptura")).setExecutor(new CommandNewRecipe(craftingManager));
        Objects.requireNonNull(this.getCommand("nowycrafting")).setExecutor(new CommandNewCrafting(craftingManager));
        Objects.requireNonNull(this.getCommand("receptury")).setExecutor(new CommandRecipes(craftingManager));
        Objects.requireNonNull(this.getCommand("usunrecepture")).setExecutor(new CommandRemoveRecipe(craftingManager));
        Objects.requireNonNull(this.getCommand("postawcrafting")).setExecutor(new CommandPlaceCrafting(craftingManager));
        Objects.requireNonNull(this.getCommand("ustawfach")).setExecutor(new CommandSetJob(statManager));
        Objects.requireNonNull(this.getCommand("nauczfachu")).setExecutor(new CommandTeachJob(statManager, teachJobManager, this));
        Objects.requireNonNull(this.getCommand("usuncrafting")).setExecutor(new CommandRemoveCrafting(craftingManager));
        Objects.requireNonNull(this.getCommand("usunfach")).setExecutor(new CommandRemoveJob(craftingManager));

        Objects.requireNonNull(this.getCommand("postawskrzynie")).setExecutor(new CommandPlaceChest());
        Objects.requireNonNull(this.getCommand("lootskrzyni")).setExecutor(new CommandChestLoot(lockpickManager));
        Objects.requireNonNull(this.getCommand("lootszanse")).setExecutor(new CommandLootChances(lockpickManager));

        Objects.requireNonNull(this.getCommand("fishchances")).setExecutor(new CommandFishChances(fishingManager));
        Objects.requireNonNull(this.getCommand("fishloot")).setExecutor(new CommandFishingLoot(fishingManager));
        Objects.requireNonNull(this.getCommand("postawlowisko")).setExecutor(new CommandPlaceFishery());

        Objects.requireNonNull(this.getCommand("przeszukaj")).setExecutor(new CommandSearch(deathManager));

        Objects.requireNonNull(this.getCommand("dodajtowar")).setExecutor(new CommandAddGood(shopManager));
        Objects.requireNonNull(this.getCommand("postawsklep")).setExecutor(new CommandPlaceShop(shopManager));

        Objects.requireNonNull(this.getCommand("dodajwlasciciela")).setExecutor(new CommandAddOwner(addingOwnerManager));
        Objects.requireNonNull(this.getCommand("dorobklucze")).setExecutor(new CommandDuplicateKey());
        Objects.requireNonNull(this.getCommand("nazwijklucz")).setExecutor(new CommandNameKey());
    }

    public StatManager getStatManager() {
        return statManager;
    }

    public BlockSheduler getBlockSheduler() {
        return blockSheduler;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        blockSheduler.placeAllBlocks();
        lockpickJsonManager.getLootManager().saveInventory(null);
        lockpickJsonManager.writeToJson();
        fishingJsonManager.writeToJson();
        craftingJsonManager.writeToJson();
        harvestJsonManager.writeToJson();
        shopJsonManager.writeToJson();
        statManager.savePlayers();
        lockedJsonManager.writeToJson();
    }
}
