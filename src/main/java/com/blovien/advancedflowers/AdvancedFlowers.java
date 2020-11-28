package com.blovien.advancedflowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedFlowers extends JavaPlugin {

    private Config configuration;
    private FlowerGui gui;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEnable() {
        try {
            Class.forName("com.destroystokyo.paper.profile.PlayerProfile");
        } catch (ClassNotFoundException e) {
            getLogger().info(ChatColor.RED + "You must use PaperSpigot to use this plugin!");
            getServer().getPluginManager().disablePlugin(this);
        }

        // Setup
        this.configuration = new Config(this);
        this.getCommand("createflower").setExecutor(this);

        this.gui = new FlowerGui();
    }

    @Override
    public void onDisable() {

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
}
