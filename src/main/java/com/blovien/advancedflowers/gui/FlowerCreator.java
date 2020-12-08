package com.blovien.advancedflowers.gui;

import com.blovien.advancedflowers.utils.Config;
import com.blovien.advancedflowers.utils.Vector2;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FlowerCreator {

    private ItemStack air = new ItemStack(Material.AIR);

    private LinkedList<ItemStack> components;
    private Inventory inventory;

    public FlowerCreator(Inventory inventory) {
        this.inventory = inventory;
        this.components = new LinkedList<>();
    }

    public void addTopFlower(ItemStack clickedItem) {
        if (clickedItem != null && !clickedItem.getType().isAir() && components.size() < 6) {
            ItemStack componentItem = inventory.getItem(Vector2.toIndex(1, components.size()));

            if (componentItem == null || componentItem.getType().isAir()) {
                inventory.setItem(Vector2.toIndex(1, components.size()), clickedItem);
                components.addLast(clickedItem);
            }
        }
    }

    public void removeTopFlower() {
        if (!components.isEmpty()) {
            components.removeLast();
            inventory.setItem(Vector2.toIndex(1, components.size()), air);
        }
    }

    public void resetFlower() {
        components.clear();

        for (int i = 0; i < 6; i++) {
            inventory.setItem(Vector2.toIndex(1, i), air);
        }
    }

    public ItemStack createFlower() {
        ItemStack pot = new ItemStack(Material.FLOWER_POT);
        ItemMeta meta = pot.getItemMeta();

        meta.setDisplayName(Config.Values.POT_TITLE.buttonString());
        meta.setLore(
                Lists.newArrayList(components.stream()
                        .map(item -> ChatColor.GRAY + item.getType().name())
                        .collect(Collectors.toCollection(LinkedList::new))
                        .descendingIterator()
                ));
        pot.setItemMeta(meta);
        return pot;
    }
}
