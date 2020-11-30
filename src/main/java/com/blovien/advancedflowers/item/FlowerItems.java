package com.blovien.advancedflowers.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

import static com.blovien.advancedflowers.utils.Config.Values.*;

public enum FlowerItems {
    LEFT_DIVISOR(Material.LIME_STAINED_GLASS_PANE),
    RIGHT_DIVISOR(Material.WHITE_STAINED_GLASS_PANE),

    TREE_SELECTOR(TREE_BUTTON_NAME.buttonString(), Material.OAK_SAPLING),
    SMALL_FLOWER_SELECTOR(SMALL_FLOWER_NAME.buttonString(), Material.DANDELION),
    TALL_FLOWER_SELECTOR(TALL_FLOWER_NAME.buttonString(),Material.SUNFLOWER),
    MISC_SELECTOR(MISC_BUTTON_NAME.buttonString(), Material.CACTUS),

    RESET_ACTION(createHeadFromTexture(RESET_BUTTON_NAME.buttonString(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==")),
    REMOVE_TOP_ACTION(createHeadFromTexture(REMOVE_TOP_BUTTON_NAME.buttonString(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQyNGRmYWYxZWUxN2Q3M2VlZWMyNDIyMTU4Y2EzM2FkMTg3ZWU3MjdhYmI3OTZmMjEzMmRlZGZkMDFmYzQ5ZSJ9fX0=")),
    CREATE_ACTION(createHeadFromTexture(CREATE_BUTTON_NAME.buttonString(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1NmJjMTI0NGZjZmY5OTM0NGYxMmFiYTQyYWMyM2ZlZTZlZjZlMzM1MWQyN2QyNzNjMTU3MjUzMWYifX19")),

    CLOSE_ACTION(CLOSE_BUTTON_NAME.buttonString(), Material.BARRIER);

    private ItemStack itemStack;
    private String name;

    FlowerItems(String name, Material material) {
        this.itemStack = new ItemStack(material);
        this.name = name;
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);
    }

    FlowerItems(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    FlowerItems(Material material) {
        this("", material);
    }

    public static FlowerItems getItemFromMaterial(ItemStack itemStack) {
        return Arrays.stream(values())
                .filter(flowerItems -> flowerItems.getItemStack().equals(itemStack))
                .findFirst()
                .orElse(null);
    }

    private static ItemStack createHeadFromTexture(String name, String texture) {
        ItemStack headItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) headItem.getItemMeta();

        PlayerProfile playerProfile = Bukkit.createProfile(UUID.randomUUID());
        playerProfile.setProperty(new ProfileProperty("textures", texture));
        //Bukkit.getUnsafe().modifyItemStack(headItem, properties);

        headMeta.setPlayerProfile(playerProfile);
        headMeta.setDisplayName(name);
        headItem.setItemMeta(headMeta);

        return headItem;
    }

    public boolean isSelector() {
        return name().toLowerCase().endsWith("selector");
    }

    public boolean isDivisor() {
        return name().toLowerCase().endsWith("divisor");
    }

    public boolean isAction() {
        return name().toLowerCase().endsWith("action");
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Material getMaterial() {
        return itemStack.getType();
    }
}
