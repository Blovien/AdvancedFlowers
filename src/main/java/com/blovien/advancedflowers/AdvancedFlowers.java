package com.blovien.advancedflowers;

import com.blovien.advancedflowers.gui.FlowerSection;
import com.blovien.advancedflowers.gui.FlowerSectionStore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedFlowers extends JavaPlugin {

    private Config configuration;
    private FlowerGui gui;
    private FlowerLogger logger;
    private FlowerSectionStore sectionStore;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEnable() {
        this.logger = new FlowerLogger(this);

        try {
            Class.forName("com.destroystokyo.paper.profile.PlayerProfile");
        } catch (ClassNotFoundException e) {
            this.logger.info(ChatColor.RED + "You must use PaperSpigot to use this plugin!");
            getServer().getPluginManager().disablePlugin(this);
        }

        this.logger.info(ChatColor.GREEN + "Enabling " + getName() + " " + getDescription().getVersion());
        // Setup
        this.saveDefaultConfig();
        this.configuration = new Config(this);

        this.sectionStore = new FlowerSectionStore();
        sectionStore.registerSections();
        this.gui = new FlowerGui().create();

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

        if (command.getName().equalsIgnoreCase("createflower")) {
            ((Player) sender).openInventory(gui.getInventory());
            return true;
        }

        return false;
    }

    public FlowerGui getGui() {
        return gui;
    }

    public Config getConfiguration() {
        return configuration;
    }

    public FlowerLogger getFlowerLogger() {
        return logger;
    }
}
