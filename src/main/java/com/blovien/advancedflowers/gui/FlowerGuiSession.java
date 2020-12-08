package com.blovien.advancedflowers.gui;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlowerGuiSession {

    private Map<Player, FlowerGui> guiStorage = new HashMap<>();

    public FlowerGuiSession() {}

    public void addInventory(Player player, FlowerGui flowerGui) {
        if (!guiStorage.containsKey(player)) {
            this.guiStorage.put(player, flowerGui);
        }
    }

    public void removeInventory(Player player) {
        this.guiStorage.remove(player);
    }

    public FlowerGui getInventory(Player player) {
        return this.guiStorage.get(player);
    }

    public void openInventory(Player player) {
        if (guiStorage.containsKey(player)) {
            player.openInventory(guiStorage.get(player).getInventory());
        } else {
            throw new RuntimeException(player.getName() + " tried to open an Advanced Flower GUI wihout having one.");
        }
    }

    public Map<Player, FlowerGui> getGuiStorageMap() {
        return Collections.unmodifiableMap(guiStorage);
    }
}
