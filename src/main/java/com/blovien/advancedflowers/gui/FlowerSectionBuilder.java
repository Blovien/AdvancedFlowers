package com.blovien.advancedflowers.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FlowerSectionBuilder {

    private ItemStack selector;
    private Collection<ItemStack> flowers;

    FlowerSectionBuilder() {
        this.flowers = new ArrayList<>();
    }

    public FlowerSectionBuilder selector(ItemStack selector) {
        this.selector = selector;
        return this;
    }

    public FlowerSectionBuilder flower(Material material) {
        this.flowers.add(new ItemStack(material));
        return this;
    }

    public FlowerSectionBuilder flower(ItemStack flower) {
        this.flowers.add(flower);
        return this;
    }

    public FlowerSectionBuilder flowers(ItemStack... flowers) {
        this.flowers.addAll(Arrays.asList(flowers));
        return this;
    }

    public FlowerSection build() {
        if (selector == null) {
            throw new RuntimeException("Item selector must be specified");
        }

        return new FlowerSection(selector, flowers);
    }
}
