package com.blovien.advancedflowers.gui;

import com.blovien.advancedflowers.utils.Config;
import com.blovien.advancedflowers.utils.Vector2;
import com.blovien.advancedflowers.gui.section.FlowerSection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.blovien.advancedflowers.item.FlowerItems.*;

public class FlowerGui {

    private final String title = Config.Values.GUI_TITLE.buttonString();
    private final byte width = 9;
    private final byte height = 6;
    private final byte size = width * height;
    private final Inventory inventory;

    private FlowerCreator flowerCreator;

    public FlowerGui(Player player) {
        this.inventory = Bukkit.createInventory(player, size, title);
        this.flowerCreator = new FlowerCreator(inventory);
    }

    public FlowerGui createGUI() {
        ItemStack leftDivisor = LEFT_DIVISOR.getItemStack();
        ItemStack rightDivisor = RIGHT_DIVISOR.getItemStack();

        // Setting left divisor columns
        for (int i = 0; i < size; i += width) {
            inventory.setItem(i, leftDivisor);
            inventory.setItem(i + 2, leftDivisor);
        }

        // Setting right divisor rows
        for (int i = width + 3; i < 2*width; i++) {
            inventory.setItem(i, rightDivisor);
            inventory.setItem(i + 3*width, rightDivisor);
        }

        // Setting flower selector
        inventory.setItem(4, TREE_SELECTOR.getItemStack());
        inventory.setItem(5, SMALL_FLOWER_SELECTOR.getItemStack());
        inventory.setItem(6, TALL_FLOWER_SELECTOR.getItemStack());
        inventory.setItem(7, MISC_SELECTOR.getItemStack());

        // Setting advanced flower editor
        inventory.setItem(Vector2.toIndex(4, 0), RESET_ACTION.getItemStack());
        inventory.setItem(Vector2.toIndex(5, 0), REMOVE_TOP_ACTION.getItemStack());
        inventory.setItem(Vector2.toIndex(6, 0), CREATE_ACTION.getItemStack());
        inventory.setItem(Vector2.toIndex(8, 0), CLOSE_ACTION.getItemStack());
        return this;
    }

    public void fillSection(FlowerSection section) {
        int flowerPosition = 0;

        for (int i = Vector2.toIndex(3, 3); i <= Vector2.toIndex(8, 3); i++) {
            inventory.setItem(i, new ItemStack(Material.AIR));
            inventory.setItem(i, section.getFlowers().get(flowerPosition));
            flowerPosition++;
        }

        for (int i = Vector2.toIndex(3, 2); i <= Vector2.toIndex(8, 2); i++) {
            inventory.setItem(i, new ItemStack(Material.AIR));
            if (flowerPosition < section.getFlowers().size()) {
                inventory.setItem(i, section.getFlowers().get(flowerPosition));
                flowerPosition++;
            }
        }
    }

    public FlowerCreator getFlowerCreator() {
        return flowerCreator;
    }

    public String getTitle() {
        return title;
    }

    public byte getWidth() {
        return width;
    }

    public byte getSize() {
        return size;
    }

    public byte getHeight() {
        return height;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
