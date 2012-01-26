package me.ryanclancy000.plugman;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlugMan extends JavaPlugin {

    private final PlugManCommands cHandler = new PlugManCommands(this);
    public PluginDescriptionFile PDF;
    Server server;
    PluginManager pm;
    ChatColor yellow = ChatColor.YELLOW;
    ChatColor green = ChatColor.GREEN;
    ChatColor white = ChatColor.WHITE;

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        server = this.getServer();
        pm = this.getServer().getPluginManager();
        PDF = this.getDescription();
        getCommand("plugman").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("plugman")) {
            return doCommand(sender, args);
        }

        return onCommand(sender, cmd, commandLabel, args);
    }

    private boolean doCommand(CommandSender sender, String args[]) {

        if (args.length == 0) {
            cHandler.thisInfo(sender);
            return true;
        }

        // Help Command - DONE

        if ("help".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.help")) {
                noPerms(sender);
                return true;
            }

            cHandler.helpList(sender);
            return true;
        }

        // Load Command

        if ("load".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.load")) {
                noPerms(sender);
                return true;
            }

            cHandler.loadPlugin();
            return true;
        }

        // Reload Command

        if ("reload".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.reload")) {
                noPerms(sender);
                return true;
            }

            cHandler.reloadPlugin(sender, args);
            return true;
        }

        // Enable Command - DONE

        if ("enable".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.enable")) {
                noPerms(sender);
                return true;
            }

            cHandler.enablePlugin(sender, args);
            return true;
        }

        // Disable Command - DONE

        if ("disable".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.disable")) {
                noPerms(sender);
                return true;
            }

            cHandler.disablePlugin(sender, args);
            return true;
        }

        // Info Command

        if ("info".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.info")) {
                noPerms(sender);
                return true;
            }

            cHandler.pluginInfo();
            return true;
        }

        // Usage Command

        if ("usage".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.usage")) {
                noPerms(sender);
                return true;
            }

            cHandler.commandUsage();
            return true;
        }

        // Describe Command

        if ("describe".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("plugman.describe")) {
                noPerms(sender);
                return true;
            }

            cHandler.describeCommand();
            return true;
        }

        cHandler.thisInfo(sender);
        return false;

    }

    public void noPerms(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You do not have permission for that command...");
    }
}