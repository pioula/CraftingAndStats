package me.pioula111.craftingandstats.pvpAndPve.death;

import org.bukkit.inventory.ItemStack;

public class MyInv {
    private ItemStack[] armorContents;
    private ItemStack itemInOffHand;
    private ItemStack[] contents;

    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public ItemStack getItemInOffHand() {
        return itemInOffHand;
    }

    public void setItemInOffHand(ItemStack offHand) {
        this.itemInOffHand = offHand;
    }

    public ItemStack[] getArmorContents() {
        return armorContents;
    }

    public void setArmorContents(ItemStack[] armorContents) {
        this.armorContents = armorContents;
    }
}
