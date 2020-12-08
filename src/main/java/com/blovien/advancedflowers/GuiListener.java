package com.blovien.advancedflowers;

import com.blovien.advancedflowers.gui.FlowerCreator;
import com.blovien.advancedflowers.gui.FlowerGui;
import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.gui.section.FlowerSectionStore;
import com.blovien.advancedflowers.gui.FlowerItems;
import com.blovien.advancedflowers.utils.Config;
import com.blovien.advancedflowers.utils.Vector2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

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

            FlowerGuiSession session = plugin.getGuiStorage();
            FlowerSectionStore sectionStorage = plugin.getSectionStore();
            FlowerGui playerGui = session.getInventory(player);
            FlowerCreator creator = playerGui.getFlowerCreator();
            ItemStack currentItem = e.getCurrentItem();

            FlowerItems clickedItem = FlowerItems.getItemFromMaterial(currentItem);
            if (!(clickedItem == null || clickedItem.isDivisor())) {
                if (clickedItem.isSelector()) {
                    session.getInventory(player).fillSection(sectionStorage.getFlowerSectionStorage().getSection(clickedItem));
                    return;
                }

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
            Vector2 slot = Vector2.vectorFromIndex(e.getSlot());

            if (slot.getX() > 2 && (slot.getY() == 2 || slot.getY() == 3)) {
                creator.addTopFlower(currentItem);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();

        if (
                (event.getBlock().getLocation().getBlockY() < 250)
                && item.getType().equals(Material.FLOWER_POT)
                && item.getItemMeta().getDisplayName().equals(Config.Values.POT_TITLE.buttonString())
        ) {
            event.setCancelled(true);
            Location location = event.getBlockPlaced().getLocation();

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
                    () -> item.getLore().stream()
                        .map(m -> Material.getMaterial(m.substring(2)))
                        .sorted(Collections.reverseOrder())
                        .forEach(material -> {
                            Block block = location.getBlock();
                            location.setY(location.getY() + 1);
                            block.setType(material, false);

                            String blockName = material.name();

                            if (blockName.endsWith("LEAVES")) {
                                Leaves leavesData = (Leaves) block.getBlockData();
                                leavesData.setPersistent(true);
                                block.setBlockData(leavesData);
                            }
                            if (blockName.endsWith("SAPLING")) {
                                Sapling saplingData = (Sapling) block.getBlockData();
                                saplingData.setStage(saplingData.getMaximumStage() - 1);
                                block.setBlockData(saplingData);
                            }
                            if (blockName.contains("CORAL")) {
                                Waterlogged waterData = (Waterlogged) block.getBlockData();
                                waterData.setWaterlogged(false);
                                block.setBlockData(waterData);
                            }
                        })
            );
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockFade(BlockFadeEvent event) {
        if (Config.Values.STOP_DYING_CORALS.buttonBoolean() && event.getBlock().getType().name().contains("CORAL")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        plugin.getGuiStorage().removeInventory(e.getPlayer());
    }
}
