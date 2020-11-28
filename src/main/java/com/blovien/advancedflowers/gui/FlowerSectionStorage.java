package com.blovien.advancedflowers.gui;

import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlowerSectionStorage {

    private Map<ItemStack, FlowerSection> sections = new HashMap<>();

    public void registerSection(FlowerSection section) {
        sections.put(section.getSelector(), section);
    }

    public FlowerSection getSection(ItemStack selector) {
        return this.sections.get(selector);
    }

    public Map<ItemStack, FlowerSection> getSections() {
        return Collections.unmodifiableMap(this.sections);
    }
}
