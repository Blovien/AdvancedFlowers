package com.blovien.advancedflowers;

import com.blovien.advancedflowers.item.FlowerItems;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static com.blovien.advancedflowers.Vector2.checkYBounds;

public class FlowerCreator {

    private ItemStack air = new ItemStack(Material.AIR);

    private final byte MAX_HEIGHT = 6;
    private ItemStack[] components;
    private Vector2 position = new Vector2(1, 0);
    private Inventory inventory;

    public FlowerCreator(Inventory inventory) {
        this.inventory = inventory;
        this.components = new ItemStack[MAX_HEIGHT];
        Arrays.fill(components, air);
    }

    public void addTopFlower(ItemStack item) {
        if (checkYBounds(position.getY())) {
            inventory.setItem(position.toIndex(), item);
            components[position.getY()] = item;
            position.addY(1);
        }
    }

    public void removeTopFlower() {
        if (checkYBounds(position.getY())) {
            inventory.setItem(position.toIndex(), air);
            components[position.getY()] = air;
            position.subY(1);
        }
    }

    public void createFlower() {
        // TODO: create pot capable of setting blocks
    }
}
