package gg.steve.bgbuddyboy.canetop.permission;

import gg.steve.bgbuddyboy.canetop.managers.ConfigManager;
import org.bukkit.command.CommandSender;

public enum PermissionNode {
    TOP("command.top"),
    RELOAD("command.reload"),
    HELP("command.help");

    private String path;

    PermissionNode(String path) {
        this.path = path;
    }

    public String get() {
        return ConfigManager.PERMISSIONS.get().getString(this.path);
    }

    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(get());
    }
}