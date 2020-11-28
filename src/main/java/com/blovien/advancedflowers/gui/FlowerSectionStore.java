package com.blovien.advancedflowers.gui;

import com.blovien.advancedflowers.FlowerItems;
import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Material;

public class FlowerSectionStore {

    private FlowerSectionStorage flowerSectionStorage;

    public FlowerSectionStore() {
        this.flowerSectionStorage = new FlowerSectionStorage();
    }

    public void registerSections() {
        FlowerSection treeSection = FlowerSection.builder()
                .selector(FlowerItems.TREE_SELECTOR.getItemStack())
                // SAPLING
                .flower(Material.OAK_SAPLING)
                .flower(Material.ACACIA_SAPLING)
                .flower(Material.BAMBOO_SAPLING)
                .flower(Material.SPRUCE_SAPLING)
                .flower(Material.BIRCH_SAPLING)
                .flower(Material.DARK_OAK_SAPLING)
                .flower(Material.JUNGLE_SAPLING)
                // LEAVES
                .flower(Material.ACACIA_LEAVES)
                .flower(Material.BIRCH_LEAVES)
                .flower(Material.DARK_OAK_LEAVES)
                .flower(Material.JUNGLE_LEAVES)
                .flower(Material.OAK_LEAVES)
                .flower(Material.SPRUCE_LEAVES)

                .build();

        FlowerSection smallFlowerSection = FlowerSection.builder()
                .selector(FlowerItems.SMALL_FLOWER_SELECTOR.getItemStack())
                // SMALL FLOWERS
                .flower(Material.DANDELION)
                .flower(Material.POPPY)
                .flower(Material.BLUE_ORCHID)
                .flower(Material.ALLIUM)
                .flower(Material.AZURE_BLUET)
                .flower(Material.RED_TULIP)
                .flower(Material.ORANGE_TULIP)
                .flower(Material.WHITE_TULIP)
                .flower(Material.PINK_TULIP)
                .flower(Material.OXEYE_DAISY)
                .flower(Material.GRASS)
                .flower(Material.DEAD_BUSH)

                .build();

        FlowerSection tallFlowerSection = FlowerSection.builder()
                .selector(FlowerItems.TALL_FLOWER_SELECTOR.getItemStack())
                // TALL FLOWERS
                .flower(Material.SUNFLOWER)
                .flower(Material.LILAC)
                .flower(Material.TALL_GRASS)
                .flower(Material.LARGE_FERN)
                .flower(Material.ROSE_BUSH)
                .flower(Material.PEONY)

                .build();

        FlowerSection miscSection = FlowerSection.builder()
                .selector(FlowerItems.MISC_SELECTOR.getItemStack())
                // MISCELLANEOUS
                .flower(Material.CACTUS)
                .flower(Material.VINE)
                .flower(Material.LILY_PAD)
                .flower(Material.BROWN_MUSHROOM)
                .flower(Material.RED_MUSHROOM)
                .flower(Material.DEAD_BUSH)
                .flower(Material.FERN)

                .build();

        this.flowerSectionStorage.registerSection(treeSection);
        this.flowerSectionStorage.registerSection(smallFlowerSection);
        this.flowerSectionStorage.registerSection(tallFlowerSection);
        this.flowerSectionStorage.registerSection(miscSection);
    }
}
