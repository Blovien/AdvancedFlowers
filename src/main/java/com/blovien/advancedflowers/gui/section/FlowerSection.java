package com.blovien.advancedflowers.gui.section;

import com.blovien.advancedflowers.item.FlowerItems;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FlowerSection {

    private FlowerItems item;
    private List<ItemStack> flowers;

    public static FlowerSectionBuilder builder() {
        return new FlowerSectionBuilder();
    }

    FlowerSection(FlowerItems item, List<ItemStack> flowers) {
        this.item = item;
        this.flowers = flowers;
    }

    public FlowerItems getItem() {
        return item;
    }

    public List<ItemStack> getFlowers() {
        return flowers;
    }
}
