package com.blovien.advancedflowers.gui.section;

import com.blovien.advancedflowers.item.FlowerItems;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlowerSectionStorage {

    private Map<FlowerItems, FlowerSection> sections = new HashMap<>();

    public void registerSection(FlowerSection section) {
        sections.put(section.getItem(), section);
    }

    public FlowerSection getSection(FlowerItems item) {
        return this.sections.get(item);
    }

    public Map<FlowerItems, FlowerSection> getSections() {
        return Collections.unmodifiableMap(this.sections);
    }
}
