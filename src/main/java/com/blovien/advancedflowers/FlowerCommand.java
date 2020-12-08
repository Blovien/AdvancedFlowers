package com.blovien.advancedflowers;

import com.blovien.advancedflowers.gui.FlowerGui;
import com.blovien.advancedflowers.gui.FlowerGuiSession;
import com.blovien.advancedflowers.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlowerCommand implements CommandExecutor {

    private FlowerGuiSession guiSession;

    public FlowerCommand(AdvancedFlowers plugin) {
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
        if (!player.hasPermission(Config.COMMAND_PERMISSION)) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        this.guiSession.addInventory(player, new FlowerGui(player).createGUI());
        this.guiSession.openInventory(player);
        return true;
    }
}
