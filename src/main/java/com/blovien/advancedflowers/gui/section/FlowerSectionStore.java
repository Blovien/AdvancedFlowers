package com.blovien.advancedflowers.gui.section;

import static com.blovien.advancedflowers.item.FlowerItems.*;
import static org.bukkit.Material.*;

public class FlowerSectionStore {

    private FlowerSectionStorage flowerSectionStorage;

    public FlowerSectionStore() {
        this.flowerSectionStorage = new FlowerSectionStorage();
    }

    public void registerSections() {
        FlowerSection treeSection = FlowerSection.builder()
                .item(TREE_SELECTOR)
                // SAPLING
                .flower(OAK_SAPLING)
                .flower(ACACIA_SAPLING)
                .flower(SPRUCE_SAPLING)
                .flower(BIRCH_SAPLING)
                .flower(DARK_OAK_SAPLING)
                .flower(JUNGLE_SAPLING)
                // LEAVES
                .flower(ACACIA_LEAVES)
                .flower(BIRCH_LEAVES)
                .flower(DARK_OAK_LEAVES)
                .flower(JUNGLE_LEAVES)
                .flower(OAK_LEAVES)
                .flower(SPRUCE_LEAVES)

                .build();

        FlowerSection smallFlowerSection = FlowerSection.builder()
                .item(SMALL_FLOWER_SELECTOR)
                // SMALL FLOWERS
                .flower(DANDELION)
                .flower(POPPY)
                .flower(BLUE_ORCHID)
                .flower(ALLIUM)
                .flower(AZURE_BLUET)
                .flower(RED_TULIP)
                .flower(ORANGE_TULIP)
                .flower(WHITE_TULIP)
                .flower(PINK_TULIP)
                .flower(OXEYE_DAISY)
                .flower(GRASS)
                .flower(FERN)

                .build();

        FlowerSection tallFlowerSection = FlowerSection.builder()
                .item(TALL_FLOWER_SELECTOR)
                // TALL FLOWERS
                .flower(SUNFLOWER)
                .flower(LILAC)
                .flower(TALL_GRASS)
                .flower(LARGE_FERN)
                .flower(ROSE_BUSH)
                .flower(PEONY)

                .build();

        FlowerSection miscSection = FlowerSection.builder()
                .item(MISC_SELECTOR)
                // MISCELLANEOUS
                .flower(CACTUS)
                .flower(VINE)
                .flower(LILY_PAD)
                .flower(BROWN_MUSHROOM)
                .flower(RED_MUSHROOM)
                .flower(DEAD_BUSH)

                .build();

        this.flowerSectionStorage.registerSection(treeSection);
        this.flowerSectionStorage.registerSection(smallFlowerSection);
        this.flowerSectionStorage.registerSection(tallFlowerSection);
        this.flowerSectionStorage.registerSection(miscSection);
    }

    public FlowerSectionStorage getFlowerSectionStorage() {
        return flowerSectionStorage;
    }
}
