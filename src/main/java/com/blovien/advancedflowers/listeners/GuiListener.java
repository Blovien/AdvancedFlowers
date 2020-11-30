package com.blovien.advancedflowers.listeners;

import com.blovien.advancedflowers.AdvancedFlowers;
import com.blovien.advancedflowers.gui.FlowerCreator;
import com.blovien.advancedflowers.gui.FlowerGui;
import com.blovien.advancedflowers.utils.Config;
import com.blovien.advancedflowers.item.FlowerItems;
import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.gui.section.FlowerSectionStore;
import com.blovien.advancedflowers.utils.Vector2;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GuiListener implements Listener {

    private AdvancedFlowers plugin;

    public GuiListener(AdvancedFlowers plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryView inventoryView = player.getOpenInventory();

        if (
                plugin.getGuiStorage().getGuiStorageMap().containsKey(player)
                && inventoryView.getTitle().equals(Config.Values.GUI_TITLE.buttonString())
        ) {
            e.setCancelled(true);
            FlowerGuiSession session = this.plugin.getGuiStorage();
            FlowerGui playerGui = session.getInventory(player);
            FlowerCreator creator = playerGui.getFlowerCreator();
            ItemStack currentItem = e.getCurrentItem();

            if (validAction(e.getClick())) {
                FlowerItems clickedItem = FlowerItems.getItemFromMaterial(currentItem);
                if (!(clickedItem == null || clickedItem.isDivisor())) {
                    if (clickedItem.isSelector()) {
                        createSection(player, clickedItem);
                    } else {
                        switch (clickedItem) {
                            case CLOSE_ACTION:
                                inventoryView.close();
                                return;
                            case RESET_ACTION:
                                creator.resetFlower();
                                return;
                            case CREATE_ACTION:
                                player.getInventory().addItem(creator.createFlower());
                                player.closeInventory(InventoryCloseEvent.Reason.CANT_USE);
                                session.removeInventory(player);
                                return;
                            case REMOVE_TOP_ACTION:
                                creator.removeTopFlower();
                                return;
                        }
                    }
                }
                Vector2 slot = Vector2.vectorFromIndex(e.getSlot());

                if (slot.getX() > 2 && (slot.getY() == 2 || slot.getY() == 3)) {
                    creator.addTopFlower(currentItem);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    @SuppressWarnings("ConstantConditions")
    public void onBlockPlace(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();

        if (
                event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                && item.getType().equals(Material.FLOWER_POT)
                && item.getItemMeta().getDisplayName().equals(Config.Values.POT_TITLE.buttonString())
        ) {
            event.setCancelled(true);
            Location location = block.getLocation();
            List<Material> materials = item.getLore().stream()
                    .map(m -> Material.getMaterial(m.substring(2)))
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());
            for (Material material : materials) {
                location.add(0, 1, 0);
                location.getBlock().setType(material, false);
            }
        }
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
