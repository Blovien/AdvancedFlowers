package com.blovien.advancedflowers;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.blovien.advancedflowers.FlowerItems.*;

public class FlowerGui {

    private String title;
    private byte width;
    private byte height;
    private byte size;
    private final Inventory inventory;

    public FlowerGui() {
        this.title = Config.Values.GUI_TITLE.buttonString();
        this.width = 9;
        this.height = 6;
        this.size = (byte) (width*height);
        this.inventory = Bukkit.createInventory(null, size);
    }

    public FlowerGui create() {
        ItemStack leftDivisor = LEFT_DIVISOR.getItemStack();
        ItemStack rightDivisor = RIGHT_DIVISOR.getItemStack();

        // Setting left divisor columns
        for (int i = 0; i < size; i += width) {
            inventory.setItem(i, leftDivisor);
            inventory.setItem(i + 2, leftDivisor);
        }

        // Setting right divisor rows
        for (int i = width + 3; i < 3*width; i++) {
            inventory.setItem(i, rightDivisor);
            inventory.setItem(i + 3*width, rightDivisor);
        }

        // Setting flower selector
        inventory.setItem(4, TREE_SELECTOR.getItemStack());
        inventory.setItem(5, SMALL_FLOWER_SELECTOR.getItemStack());
        inventory.setItem(6, TALL_FLOWER_SELECTOR.getItemStack());
        inventory.setItem(7, MISC_SELECTOR.getItemStack());

        // Setting advanced flower editor
        byte traslation = (byte) ((height-1)*width);
        inventory.setItem(traslation + 4, RESET_ACTION.getItemStack());
        inventory.setItem(traslation + 5, REMOVE_TOP_ACTION.getItemStack());
        inventory.setItem(traslation + 6, CREATE_ACTION.getItemStack());
        inventory.setItem(traslation + 8, CLOSE_ACTION.getItemStack());

        return this;
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
