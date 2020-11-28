package com.blovien.advancedflowers;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class FlowerLogger {

    private Logger logger;
    private static final String prefix = "[AdvancedFlowers]";

    public FlowerLogger(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
    }

    public void info(String message) {
        this.logger.info(prefix + " " + message);
    }
}
