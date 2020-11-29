package com.blovien.advancedflowers.item;

import org.bukkit.entity.Player;

public interface ItemHandler {

    default void handleItem(Player player) {}
}
