package com.blovien.advancedflowers.gui;

import com.blovien.advancedflowers.utils.Config;
import com.blovien.advancedflowers.utils.Vector2;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlowerCreator {

    private ItemStack air = new ItemStack(Material.AIR);

    private List<ItemStack> components;
    private Vector2 position = new Vector2(1, 0);
    private Inventory inventory;

    public FlowerCreator(Inventory inventory) {
        this.inventory = inventory;
        this.components = new ArrayList<>();
    }

    public void addTopFlower(ItemStack clickedItem) {
        if (clickedItem != null && !clickedItem.getType().isAir() && (position.getY() < 6) ) {
            ItemStack componentItem = inventory.getItem(position.toIndex());
            if (componentItem == null || componentItem.getType().isAir()) {
                inventory.setItem(position.toIndex(), clickedItem);
                components.add(clickedItem);
                position.addY(1);
            }
        }
    }

    public void removeTopFlower() {
        if (position.getY() >= 1) {
            position.subY(1);
            inventory.setItem(position.toIndex(), air);
            components.remove(position.getY());
        }
    }

    public void resetFlower() {
        this.position = new Vector2(1, 0);
        components.clear();

        for (int i = 0; i < 6; i++) {
            inventory.setItem(Vector2.toIndex(1, i), air);
        }
    }

    public ItemStack createFlower() {
        ItemStack pot = new ItemStack(Material.FLOWER_POT);
        ItemMeta meta = pot.getItemMeta();

        meta.setDisplayName(Config.Values.POT_TITLE.buttonString());
        List<String> componentNameList = new ArrayList<>();

        for (ItemStack component : components) {
            componentNameList.add(ChatColor.GRAY + component.getType().name());
        }

        Collections.reverse(componentNameList);
        meta.setLore(componentNameList);
        pot.setItemMeta(meta);
        return pot;
    }
}
