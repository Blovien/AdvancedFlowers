package com.blovien.advancedflowers.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class Config {

    private static FileConfiguration config;

    public static final String COMMAND_PERMISSION = "createflower.use";

    public Config(JavaPlugin plugin) {
        config = plugin.getConfig();
    }

    public enum Values {
        GUI_TITLE("gui-title"),
        POT_TITLE("pot-title"),

        TREE_BUTTON_NAME("button-name.tree"),
        SMALL_FLOWER_NAME("button-name.small-flower"),
        TALL_FLOWER_NAME("button-name.tall-flower"),
        MISC_BUTTON_NAME("button-name.misc"),
        CORAL_BUTTON_NAME("button-name.coral"),

        REMOVE_TOP_BUTTON_NAME("button-name.remove-top"),
        RESET_BUTTON_NAME("button-name.reset"),
        CREATE_BUTTON_NAME("button-name.create"),
        CLOSE_BUTTON_NAME("button-name.close"),

        STOP_DYING_CORALS("stop-dying-corals");

        private String name;

        Values(String name) {
            this.name = name;
        }

        public String buttonString() {
            return ChatColor.translateAlternateColorCodes(
                    '&',
                    Optional.ofNullable(config.getString(name))
                            .orElse(name())
            );
        }

        public boolean buttonBoolean() {
            return config.getBoolean(name);
        }
    }
}
