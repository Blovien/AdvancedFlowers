package com.blovien.advancedflowers.gui.section;

import com.blovien.advancedflowers.item.FlowerItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowerSectionBuilder {

    private FlowerItems item;
    private List<ItemStack> flowers;

    FlowerSectionBuilder() {
        this.flowers = new ArrayList<>();
    }

    public FlowerSectionBuilder item(FlowerItems item) {
        this.item = item;
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
        if (item == null) {
            throw new RuntimeException("Item selector must be specified");
        }

        return new FlowerSection(item, flowers);
    }
}
