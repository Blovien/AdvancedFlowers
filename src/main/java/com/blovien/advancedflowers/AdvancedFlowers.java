package com.blovien.advancedflowers;

import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.gui.section.FlowerSectionStore;
import com.blovien.advancedflowers.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public final class AdvancedFlowers extends JavaPlugin {

    private Config configuration;
    private FlowerGuiSession guiSession;
    private FlowerSectionStore sectionStore;
    private Logger logger;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEnable() {

        try {
            Class.forName("com.destroystokyo.paper.profile.PlayerProfile");
        } catch (ClassNotFoundException e) {
            getLogger().info(ChatColor.RED + "You must use PaperSpigot to use this plugin!");
            getServer().getPluginManager().disablePlugin(this);
        }

        // Starting...
        this.logger = getSLF4JLogger();
        // Setup
        this.saveDefaultConfig();
        //noinspection InstantiationOfUtilityClass
        this.configuration = new Config(this);

        this.sectionStore = new FlowerSectionStore();
        sectionStore.registerSections();
        this.guiSession = new FlowerGuiSession();

        this.getServer().getPluginManager().registerEvents(new GuiListener(this), this);
        this.getCommand("createflower").setExecutor(new FlowerCommand(this));

        this.logger.info(ChatColor.GREEN + "Enabled!");
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
}
