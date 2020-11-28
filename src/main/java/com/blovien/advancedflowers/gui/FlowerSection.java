package com.blovien.advancedflowers.gui;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class FlowerSection {

    private ItemStack selector;
    private Collection<ItemStack> flowers;

    public static FlowerSectionBuilder builder() {
        return new FlowerSectionBuilder();
    }

    FlowerSection(ItemStack selector, Collection<ItemStack> flowers) {
        this.selector = selector;
        this.flowers = flowers;
    }

    public ItemStack getSelector() {
        return selector;
    }

    public Collection<ItemStack> getFlowers() {
        return flowers;
    }
}
