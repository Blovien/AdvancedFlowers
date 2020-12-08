package com.blovien.advancedflowers;

import com.blovien.advancedflowers.gui.FlowerGui;
import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.utils.Config;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class FlowerCommand implements CommandExecutor, TabCompleter {

    private FlowerGuiSession guiSession;
    private AdvancedFlowers plugin;

    public FlowerCommand(AdvancedFlowers plugin) {
        this.plugin = plugin;
        this.guiSession = plugin.getGuiStorage();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only for players!");
            return false;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("reload") && player.hasPermission(Config.RELOAD_PERMISSION)) {
            plugin.reloadConfig();
            player.sendMessage(ChatColor.GREEN + "AdvancedFlowers' configuration reloaded!");
            return true;
        }

        if (player.hasPermission(Config.COMMAND_PERMISSION)) {
            player.sendMessage(ChatColor.GREEN + "Opening AdvancedFlowers GUI...");
            this.guiSession.addInventory(player, new FlowerGui(player).createGUI());
            this.guiSession.openInventory(player);
            return true;
        }

        player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Lists.newArrayList("reload");
    }
}
