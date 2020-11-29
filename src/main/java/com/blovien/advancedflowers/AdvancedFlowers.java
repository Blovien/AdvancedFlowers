package com.blovien.advancedflowers;

import com.blovien.advancedflowers.gui.FlowerGui;
import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.gui.section.FlowerSectionStore;
import com.blovien.advancedflowers.listeners.GuiListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public final class AdvancedFlowers extends JavaPlugin {

    private Config configuration;
    private FlowerGuiSession guiSession;
    private FlowerSectionStore sectionStore;
    private Logger logger;

    private static AdvancedFlowers INSTANCE;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEnable() {

        try {
            Class.forName("com.destroystokyo.paper.profile.PlayerProfile");
        } catch (ClassNotFoundException e) {
            getLogger().info(ChatColor.RED + "You must use PaperSpigot to use this plugin!");
            getServer().getPluginManager().disablePlugin(this);
        }

        INSTANCE = this;
        this.logger = getSLF4JLogger();
        this.logger.info(ChatColor.GREEN + "Enabling " + getName() + " " + getDescription().getVersion());
        // Setup
        this.saveDefaultConfig();
        this.configuration = new Config(this);

        this.sectionStore = new FlowerSectionStore();
        sectionStore.registerSections();
        this.guiSession = new FlowerGuiSession();

        this.getServer().getPluginManager().registerEvents(new GuiListener(this), this);
        this.getCommand("createflower").setExecutor(this);

        this.logger.info(ChatColor.GREEN + "Enabled!");
    }

    @Override
    public void onDisable() {
        this.logger.info(ChatColor.RED + "Disabling...");
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only for players!");
            return false;
        }

        Player player = (Player) sender;
        this.guiSession.addInventory(player, new FlowerGui(player).createGUI());
        this.guiSession.openInventory(player);
        return true;
    }

    public Config getConfiguration() {
        return configuration;
    }

    public FlowerSectionStore getSectionStore() {
        return sectionStore;
    }

    public FlowerGuiSession getGuiStorage() {
        return guiSession;
    }

    public static AdvancedFlowers getInstance() {
        return INSTANCE;
    }
}
