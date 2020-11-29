package com.blovien.advancedflowers.listeners;

import com.blovien.advancedflowers.AdvancedFlowers;
import com.blovien.advancedflowers.Config;
import com.blovien.advancedflowers.item.FlowerItems;
import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.gui.section.FlowerSectionStore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;

public class GuiListener implements Listener {

    private AdvancedFlowers plugin;

    public GuiListener(AdvancedFlowers plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryView inventoryView = player.getOpenInventory();

        if (plugin.getGuiStorage().getGuiStorageMap().containsKey(player)
                && inventoryView.getTitle().equals(Config.Values.GUI_TITLE.buttonString())) {
            e.setCancelled(true);

            if (validAction(e.getClick())) {
                FlowerItems clickedItem = FlowerItems.getItemFromMaterial(e.getCurrentItem());
                if (!(clickedItem == null || clickedItem.isDivisor())) {
                    if (clickedItem.isSelector()) {
                        createSection(player, clickedItem);
                    } else {
                        switch (clickedItem) {
                            case CLOSE_ACTION:
                                inventoryView.close();
                                return;
                            case RESET_ACTION:
                                return;
                            case CREATE_ACTION:
                                return;
                            case REMOVE_TOP_ACTION:
                                return;
                        }
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        // TODO: pot interactions
    }

    private void createSection(Player player, FlowerItems selector) {
        FlowerGuiSession session = plugin.getGuiStorage();
        FlowerSectionStore sectionStorage = plugin.getSectionStore();
        session.getInventory(player).fillSection(sectionStorage.getFlowerSectionStorage().getSection(selector));
    }

    private boolean validAction(ClickType type) {
        switch (type) {
            case LEFT:
            case RIGHT:
            case DOUBLE_CLICK:
            case MIDDLE:
                return true;
            default:
                return false;
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        plugin.getGuiStorage().removeInventory(e.getPlayer());
    }
}
